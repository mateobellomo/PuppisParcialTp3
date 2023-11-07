package com.proyecto.personal.puppisparcialtp3.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.personal.puppisparcialtp3.domain.GetAllDogsBreedsUseCase
import com.proyecto.personal.puppisparcialtp3.domain.GetDogsImageUseCase
import com.proyecto.personal.puppisparcialtp3.domain.GetSpecificBreedImagesUseCase
import com.proyecto.personal.puppisparcialtp3.entities.Gender
import com.proyecto.personal.puppisparcialtp3.entities.Location
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

    val listUrl : MutableList<String> = ArrayList()

    val listPet : MutableList<Pet> = ArrayList()

    val listUrlBeagle : MutableList<String> = ArrayList()

    val listUrlChow : MutableList<String> = ArrayList()

    val listUrlLabrador : MutableList<String> = ArrayList()


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

        listUrlChow.add("https://images.dog.ceo/breeds/husky/n02110185_12498.jpg")
        listUrlChow.add("https://images.dog.ceo/breeds/husky/n02110185_12498.jpg")




        listPet.add(Pet("Mateo", "5", "beagle", "N/A", "MALE", "Brown", "10", "BUENOS_AIRES", "Agustin", listUrlBeagle,  false, isFavorite = false));
        listPet.add(Pet("Paola", "6", "chow", "N/A", "FEMALE", "White", "30", "CORDOBA", "Carlos", listUrlChow,  false, isFavorite = false));
        //listPet.add(Pet("Yanina", 2, "labrador", "N/A", "FEMALE", "Black", 20.0, Location.SALTA, "Agustin", false, isFavorite = false));
        //listPet.add(Pet("Camila", 8, "akita", "N/A", "FEMALE", "White", 15.0, Location.MENDOZA, "Daniel", false, isFavorite = false));
        //listPet.add(Pet("Agustin", 5, "beagle", "N/A", "MALE", "Brown", 10.0, Location.BUENOS_AIRES, "Pedro", false, isFavorite = false));
        //listPet.add(Pet("Francisco", 6, "chow", "N/A", "MALE", "White", 30.0, Location.CORDOBA, "Mariella", false, isFavorite = false));
        //listPet.add(Pet("Javier", 2, "labrador", "N/A", "MALE", "Black", 20.0, Location.SALTA, "Agustin", false, isFavorite = false));
        //listPet.add(Pet("Micaela", 8, "akita", "N/A", "FEMALE", "White", 15.0, Location.MENDOZA, "Juan", false, isFavorite = false));
        //listPet.add(Pet("Jimena", 2, "labrador", "N/A", "FEMALE", "Black", 20.0, Location.SALTA, "Fernando", false, isFavorite = false));
        //listPet.add(Pet("Ariel", 8, "akita", "N/A", "MALE", "Black", 15.0, Location.MENDOZA, "Agustin", false, isFavorite = false));


        pets.postValue(listPet)
    }

    fun newPet(){
        listPet.add(Pet("Prueba", "5", "beagle", "N/A", "MALE", "Brown", "10", "BUENOS_AIRES", "Agustin", listUrlBeagle,  false, isFavorite = false));

        pets.postValue(listPet)
    }

    fun getPets() : MutableList<Pet>{
        return listPet
    }




}