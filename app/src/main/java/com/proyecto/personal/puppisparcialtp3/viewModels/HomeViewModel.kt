package com.proyecto.personal.puppisparcialtp3.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.personal.puppisparcialtp3.domain.GetAllDogsBreedsUseCase
import com.proyecto.personal.puppisparcialtp3.domain.GetDogsImageUseCase
import com.proyecto.personal.puppisparcialtp3.domain.GetSpecificBreedImagesUseCase
import com.proyecto.personal.puppisparcialtp3.entities.Pet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDogsImage :GetDogsImageUseCase,
    private val getAllDogsBreedsUseCase: GetAllDogsBreedsUseCase,
    private val getSpecificBreedImages :GetSpecificBreedImagesUseCase) : ViewModel() {
    val pets = MutableLiveData<List<Pet>?>()

    val listUrl : MutableList<String> = ArrayList()

    val listPet : MutableList<Pet> = ArrayList()

    val dogsIamges = MutableLiveData<List<String>>()
    val filters = MutableLiveData<List<String>>()
    val originalPetList =  MutableLiveData<List<Pet>>()

    fun onCreate() {

        viewModelScope.launch {
            // imagenes ramdom de perros , strings con direcciones url
            val result = getDogsImage()
            Log.d("respuesta", " esta es getDogsImage ${result.dogsImage}")

            //la lista de razas de perros, Strings
            val result2 = getAllDogsBreedsUseCase()
            Log.d("respuesta", " esta es getAllDogsBreedsUseCase ${result2.toString()}")

            //obtiene imagenes de la raza pasada x parametro, cuantas imagenes se indique
            val result3 = getSpecificBreedImages( result2?.get(5).toString(), 2)
            Log.d("respuesta", " esta es la getSpecificBreedImages ${result3.toString()}")


        }
    }

    fun createPet(){
        listUrl.add("https://images.dog.ceo/breeds/terrier-toy/n02087046_7037.jpg")
        listUrl.add("https://images.dog.ceo/breeds/husky/n02110185_12498.jpg")

        listPet.add(Pet("Agustin", 1, "beagle", "shiba","Male","Nada","30","Buenos Aires","Agustin", listUrl, false))
        listPet.add(Pet("Paola", 15, "chow", "shiba","Male","Nada","30","Catamarca","Agustin", listUrl, false))
        listPet.add(Pet("Yanina", 2, "labrador", "shiba","Male","Nada","30","Chubut","Agustin", listUrl, false))
        listPet.add(Pet("Sebastian", 3, "pug", "shiba","Male","Nada","30","Corrientes","Agustin", listUrl, false))
        listPet.add(Pet("Camila", 10, "doberman", "shiba","Female","Nada","30","Corrientes","Agustin", listUrl, false))
        pets.postValue(listPet)
        originalPetList.postValue(listPet)
    }

    fun newPet(){
        listPet.add(Pet("PRUEBA", 10, "beagle", "shiba","Male","Nada","30","BS AS","Agustin", listUrl, false))

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


}