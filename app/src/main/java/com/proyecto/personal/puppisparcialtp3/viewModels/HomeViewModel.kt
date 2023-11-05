package com.proyecto.personal.puppisparcialtp3.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
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

    val pets = MutableLiveData<List<Pet>>()

    val petsFavorite = MutableLiveData<List<Pet>>()

    val listUrl : MutableList<String> = ArrayList()

    val listPet : MutableList<Pet> = ArrayList()

    var listPetAuxiliar : MutableList<Pet> = ArrayList()

    val dogsIamges = MutableLiveData<List<String>>()

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

        listPet.add(Pet("Mateo", 10, "beagle", "shiba","Male","Brown","30","Buenos Aires","Agustin", listUrl, false, false))
        listPet.add(Pet("Agustin", 10, "beagle", "shiba","Male","Brown","30","Buenos Aires","Valentina", listUrl, false, false))
        listPet.add(Pet("Paola", 10, "chow", "shiba","Male","Brown","30","Formosa","Agustin", listUrl, false, false))
        listPet.add(Pet("Yanina", 10, "labrador", "shiba","Male","Black","30","Mendoza","Agustin", listUrl, false, false))
        listPet.add(Pet("Camila", 10, "pug", "shiba","Male","White","30","Formosa","Sofia", listUrl, false, false))
        listPet.add(Pet("Francisco", 10, "beagle", "shiba","Male","White","30","Neuquen","Agustin", listUrl, false, false))
        listPet.add(Pet("Javier", 10, "chow", "shiba","Male","White","30","Buenos Aires","Agustin", listUrl, false, false))
        listPet.add(Pet("Pedro", 10, "labrador", "shiba","Male","White","30","Mendoza","Belen", listUrl, false, false))
        listPet.add(Pet("Martin", 10, "pug", "shiba","Male","Brown","30","Neuquen","Agustin", listUrl, false, false))
        listPet.add(Pet("Micaela", 10, "pug", "shiba","Male","Brown","30","Neuquen","Agustina", listUrl, false, false))


        pets.postValue(listPet)
    }

    fun newPet(){
        listPet.add(Pet("PRUEBA", 10, "beagle", "shiba","Male","Nada","30","BS AS","Agustin", listUrl, false, false))

        pets.postValue(listPet)
    }

    fun addFavorite(pet: Pet){
        listPetAuxiliar = petsFavorite.value?.toMutableList() ?: mutableListOf()

        listPetAuxiliar.add(pet)

        petsFavorite.postValue(listPetAuxiliar)
    }

}