package com.proyecto.personal.puppisparcialtp3.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.personal.puppisparcialtp3.data.model.DogsModel
import com.proyecto.personal.puppisparcialtp3.domain.useCases.GetAllDogsBreedsUseCase
import com.proyecto.personal.puppisparcialtp3.domain.useCases.GetDogsImageUseCase
import com.proyecto.personal.puppisparcialtp3.domain.useCases.GetSpecificBreedImagesUseCase
import com.proyecto.personal.puppisparcialtp3.utils.Gender
import com.proyecto.personal.puppisparcialtp3.utils.Location
import com.proyecto.personal.puppisparcialtp3.data.model.PetEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDogsImage: GetDogsImageUseCase,
    private val getAllDogsBreedsUseCase: GetAllDogsBreedsUseCase,
    private val getSpecificBreedImages: GetSpecificBreedImagesUseCase
) : ViewModel() {
    val pets = MutableLiveData<List<PetEntity>?>()

    val listUrl: MutableList<String> = ArrayList()

    val listPet: MutableList<PetEntity> = ArrayList()

    val dogsIamges = MutableLiveData<List<String>>()
    val filters = MutableLiveData<List<String>>()
    val originalPetList =  MutableLiveData<List<PetEntity>>()
    val dogBreedSugestions: MutableLiveData<List<String>> = MutableLiveData()

    fun onCreate() {

        viewModelScope.launch {
            // imagenes ramdom de perros , strings con direcciones url
            val result = getDogsImage()
            Log.d("respuesta", " esta es getDogsImage ${result.dogsImage}")

            //la lista de razas de perros, Strings
            val result2 = getAllDogsBreedsUseCase()
            Log.d("respuesta", " esta es getAllDogsBreedsUseCase ${result2.toString()}")

            //obtiene imagenes de la raza pasada x parametro, cuantas imagenes se indique
            val result3 = getSpecificBreedImages(result2?.get(5).toString(), 2)
            Log.d("respuesta", " esta es la getSpecificBreedImages ${result3.toString()}")


            createPet(result)
            if (result2 != null) {
                updateDogBreeds(result2)
            }


        }
    }

    fun createPet(result:DogsModel) {
   if(listPet.isEmpty()){
        listPet.add(
            PetEntity(
                "Agustin",
                "10 months",
                "beagle",
                "shiba",
                Gender.MALE,
                "Nada",
                "30 gr",
                Location.BUENOS_AIRES,
                "Agustin",
                photo = result.dogsImage.get(2),
                false,
                isFavorite = false
            )
        )
        listPet.add(
            PetEntity(
                "Paola",
                "10 days",
                "chow",
                "shiba",
                Gender.FEMALE,
                "Nada",
                "30 kg",
                Location.BUENOS_AIRES,
                "Agustin",
                photo = result.dogsImage.get(0),
                false,
                isFavorite = false
            )
        )
        listPet.add(
            PetEntity(
                "Yanina",
                "10 years",
                "labrador",
                "shiba",
                Gender.MALE,
                "Nada",
                "30 kgs",
                Location.BUENOS_AIRES,
                "Agustin",
                photo = result.dogsImage.get(4),
                false,
                isFavorite = false
            )
        )
        listPet.add(
            PetEntity(
                "Camila",
                "10",
                "pug",
                "shiba",
                Gender.MALE,
                "Nada",
                "30.0",
                Location.BUENOS_AIRES,
                "Agustin",
                photo = result.dogsImage.get(5),
                false,
                isFavorite = false
            )
        )

       pets.postValue(listPet)
       originalPetList.postValue(listPet)
   }


    }

    fun newPet() {
        listPet.add(
            PetEntity(
                "PRUEBA",
                "10",
                "beagle",
                "shiba",
                Gender.MALE,
                "Nada",
                "30.0",
                Location.BUENOS_AIRES,
                "Agustin",
                photo = "",
                false,
                isFavorite = false
            )
        )

        pets.postValue(listPet)
    }


    fun addFilter(newFilter: String) {
        val oldList = filters.value?.toMutableList() ?: mutableListOf()
        oldList.add(newFilter)
        Log.d("filters" ,"luego de agregar ${filters.value.toString()}")
        filters.postValue(oldList)
    }

    fun deleteFilter(filterToDelete: String) {
        val oldList = filters.value?.toMutableList() ?: mutableListOf()
        oldList.remove(filterToDelete)
        filters.postValue(oldList)
    }

    fun clearList() {
        filters.postValue(emptyList())
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
        // En tu ViewModel (suponiendo que listPet es una MutableLiveData<List<Pet>?>)
        val razasDisponibles: Set<String> = listPet?.flatMap { pet ->
            val razas = mutableListOf<String>()
            pet.breed?.let { razas.add(it) }
            pet.subBreed?.let { razas.add(it) }
            razas
        }?.toSet() ?: emptySet()
     return razasDisponibles.toTypedArray()

    }



}