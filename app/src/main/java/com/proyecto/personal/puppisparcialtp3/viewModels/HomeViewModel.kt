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

    val listUrlBeagle : MutableList<String> = ArrayList()

    val listUrlChow : MutableList<String> = ArrayList()

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
        listUrlBeagle.add("https://images.dog.ceo/breeds/beagle/1271553739_Milo.jpg")
        listUrlBeagle.add("https://images.dog.ceo/breeds/beagle/1374053345_Milo.jpg")
        listUrlBeagle.add("https://images.dog.ceo/breeds/beagle/166407056_Milo.jpg")
        listUrlBeagle.add("https://images.dog.ceo/breeds/beagle/184369380_Milo.jpg")
        listUrlBeagle.add("https://images.dog.ceo/breeds/beagle/603525417_Milo.jpg")
        listUrlChow.add("https://images.dog.ceo/breeds/husky/n02110185_12498.jpg")
        listUrlChow.add("https://images.dog.ceo/breeds/husky/n02110185_12498.jpg")
        listUrlChow.add("https://images.dog.ceo/breeds/husky/n02110185_12498.jpg")
        listUrlChow.add("https://images.dog.ceo/breeds/husky/n02110185_12498.jpg")
        listUrlChow.add("https://images.dog.ceo/breeds/husky/n02110185_12498.jpg")

        // Faltan URL de mas razas


        listPet.add(Pet("Mateo", 1, "beagle", "shiba","Male","Brown","10","Buenos Aires","Agustin", listUrlBeagle, false, false))
        listPet.add(Pet("Agustin", 2, "beagle", "shiba","Male","Brown","15","Buenos Aires","Valentina", listUrlChow, false, false))
        listPet.add(Pet("Paola", 2, "chow", "shiba","Male","Brown","20","Formosa","Agustin", listUrlChow, false, false))
        listPet.add(Pet("Yanina", 1, "labrador", "shiba","Male","Black","10\"","Mendoza","Agustin", listUrlChow, false, false))
        listPet.add(Pet("Camila", 1, "pug", "shiba","Male","White","20","Formosa","Sofia", listUrlChow, false, false))
        listPet.add(Pet("Francisco", 4, "beagle", "shiba","Male","White","15","Neuquen","Agustin", listUrlChow, false, false))
        listPet.add(Pet("Javier", 3, "chow", "shiba","Male","White","20","Buenos Aires","Agustin", listUrlChow, false, false))
        listPet.add(Pet("Pedro", 2, "labrador", "shiba","Male","White","10","Mendoza","Belen", listUrlChow, false, false))
        listPet.add(Pet("Martin", 6, "pug", "shiba","Male","Brown","20","Neuquen","Agustin", listUrlChow, false, false))
        listPet.add(Pet("Micaela", 4, "pug", "shiba","Male","Brown","20","Neuquen","Agustina", listUrlChow, false, false))


        pets.postValue(listPet)
    }

    fun newPet(){
        listPet.add(Pet("Jimena", 10, "beagle", "shiba","Male","Brown","30","BS AS","Agustin", listUrlChow, false, false))

        pets.postValue(listPet)
    }

    fun addFavorite(pet: Pet){
        listPetAuxiliar = petsFavorite.value?.toMutableList() ?: mutableListOf()

        listPetAuxiliar.add(pet)

        petsFavorite.postValue(listPetAuxiliar)
    }

}