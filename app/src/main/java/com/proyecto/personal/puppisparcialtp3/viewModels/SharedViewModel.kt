package com.proyecto.personal.puppisparcialtp3.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.personal.puppisparcialtp3.domain.useCases.GetAllDogsBreedsUseCase
import com.proyecto.personal.puppisparcialtp3.domain.useCases.GetDogsImageUseCase
import com.proyecto.personal.puppisparcialtp3.domain.useCases.GetSpecificBreedImagesUseCase
import com.proyecto.personal.puppisparcialtp3.domain.Pet
import com.proyecto.personal.puppisparcialtp3.domain.useCases.GetSomeDogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val getDogsImage: GetDogsImageUseCase,
    private val getAllDogsBreedsUseCase: GetAllDogsBreedsUseCase,
    private val getSpecificBreedImages: GetSpecificBreedImagesUseCase,
    private val getSomeDogsUseCase : GetSomeDogsUseCase
) : ViewModel() {

    val pets = MutableLiveData<List<Pet>?>()
    private val _images = MutableLiveData<List<String>>()
    val images: LiveData<List<String>> get() = _images
    private val _breedListLiveData = MutableLiveData<List<Pair<String, List<String>>>?>()
    val breedListLiveData: MutableLiveData<List<Pair<String, List<String>>>?>
        get() = _breedListLiveData

    private val originalPetList =  MutableLiveData<List<Pet>>()
    val dogBreedSuggestions: MutableLiveData<List<String>> = MutableLiveData()

    fun onCreate() {

        viewModelScope.launch {
            // imagenes ramdom de perros , strings con direcciones url
            val result = getDogsImage()
            //la lista de razas de perros, Strings
            val result2 = getAllDogsBreedsUseCase()

            val repositoryPets = getSomeDogsUseCase()

            pets.postValue(repositoryPets)
            originalPetList.postValue(repositoryPets)

            if (result2 != null) {
                // junto razas y sub razas en una misma lista
                val breedAndSubBreeds =result2.flatMap { listOf(it.first) + it.second }
                updateDogBreeds(breedAndSubBreeds)
                _breedListLiveData.postValue(result2)
            }

        }
    }

    fun imageForPost(breed:String,number:Int) {
        viewModelScope.launch {
            val result  = getSpecificBreedImages(breed, number)
            _images.postValue(result.dogsImage)
            Log.d("imagenes", "el resultado de las imagens ${result.toString()}")
        }

    }

    private fun updateDogBreeds(newList: List<String>){
        dogBreedSuggestions.value =newList
    }

    fun availableBreed () :Array<String>{
        val allPets = pets.value
        Log.e("razas disponibles", pets.value.toString())
        val availableBreeds: Set<String> = allPets?.flatMap { pet ->
            val razas = mutableListOf<String>()
            pet.breed.let { razas.add(it) }
            pet.subBreed?.let { razas.add(it) }
            razas
        }?.toSet() ?: emptySet()
        Log.e("razas disponibles", availableBreeds.toString())
        return availableBreeds.toTypedArray()

    }


    fun onFavoritesClick(pet: Pet) {
        val currentList = pets.value
        currentList?.let {
            val updatedList = it.map { currentPet ->
                if (currentPet.id == pet.id) {
                    currentPet.copy(isFavorite = !currentPet.isFavorite) // Cambia el estado de favorito
                } else {
                    currentPet
                }
            }
            pets.postValue(updatedList)
        }
    }

    fun findPet (id : Long) : Pet? {
        val list = pets.value
        val petToLookFor = list?.find { it.id == id }

        if (petToLookFor != null) {
          return petToLookFor
        } else {
            return null
        }
    }

    fun addPet (pet:Pet){
        var list = pets.value
        if (list != null) {
            list = list.toMutableList()
            list.add(pet)
            pets.value = list
        }
    }

}


