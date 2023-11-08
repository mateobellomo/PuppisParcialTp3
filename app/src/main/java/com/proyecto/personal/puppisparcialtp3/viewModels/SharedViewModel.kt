package com.proyecto.personal.puppisparcialtp3.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.personal.puppisparcialtp3.data.model.DogsModel
import com.proyecto.personal.puppisparcialtp3.domain.useCases.GetAllDogsBreedsUseCase
import com.proyecto.personal.puppisparcialtp3.domain.useCases.GetDogsImageUseCase
import com.proyecto.personal.puppisparcialtp3.domain.useCases.GetSpecificBreedImagesUseCase
import com.proyecto.personal.puppisparcialtp3.utils.Gender
import com.proyecto.personal.puppisparcialtp3.utils.Location
import com.proyecto.personal.puppisparcialtp3.domain.Pet
import com.proyecto.personal.puppisparcialtp3.domain.useCases.GetSomeDogsUseCase
import com.proyecto.personal.puppisparcialtp3.listeners.OnFavoritesClickListener
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
    val listUrl: MutableList<String> = ArrayList()
    private val _breedListLiveData = MutableLiveData<List<Pair<String, List<String>>>?>()
    val breedListLiveData: MutableLiveData<List<Pair<String, List<String>>>?>
        get() = _breedListLiveData
    val dogsIamges = MutableLiveData<List<String>>()

    val originalPetList =  MutableLiveData<List<Pet>>()
    val dogBreedSugestions: MutableLiveData<List<String>> = MutableLiveData()

    fun onCreate() {

        viewModelScope.launch {
            // imagenes ramdom de perros , strings con direcciones url
            val result = getDogsImage()
            //la lista de razas de perros, Strings
            val result2 = getAllDogsBreedsUseCase()
            //obtiene imagenes de la raza pasada x parametro, cuantas imagenes se indique
            val result3 = getSpecificBreedImages(result2?.get(5).toString(), 2)
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


    fun resetOriginalList(){
        val originalPet = originalPetList.value
        if (!originalPet.isNullOrEmpty()){
            pets.postValue(originalPet)


        }
    }

    fun updateDogBreeds(newList: List<String>){
        dogBreedSugestions.value =newList
    }

    fun availablesBreed () :Array<String>{
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

    suspend fun getImage(): String {
        var result: String = ""
        viewModelScope.launch {
            // imagenes ramdom de perros , strings con direcciones url
            val response = getDogsImage()

                result = response.dogsImage?.get(1) ?: ""

        }
        return result
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


