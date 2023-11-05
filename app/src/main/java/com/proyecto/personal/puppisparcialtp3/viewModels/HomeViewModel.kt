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
    private val getDogsImage: GetDogsImageUseCase,
    private val getAllDogsBreedsUseCase: GetAllDogsBreedsUseCase,
    private val getSpecificBreedImages: GetSpecificBreedImagesUseCase
) : ViewModel() {
    val pets = MutableLiveData<List<Pet>>()

    val listUrl: MutableList<String> = ArrayList()

    val listPet: MutableList<Pet> = ArrayList()

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
            val result3 = getSpecificBreedImages(result2?.get(5).toString(), 2)
            Log.d("respuesta", " esta es la getSpecificBreedImages ${result3.toString()}")


        }
    }

    fun createPet() {
        listUrl.add("https://images.dog.ceo/breeds/terrier-toy/n02087046_7037.jpg")
        listUrl.add("https://images.dog.ceo/breeds/husky/n02110185_12498.jpg")

        listPet.add(
            Pet(
                "Agustin",
                10,
                "beagle",
                "shiba",
                Gender.MALE,
                "Nada",
                30.0,
                Location.BUENOS_AIRES,
                "Agustin",
                false,
                isFavorite = false
            )
        )
        listPet.add(
            Pet(
                "Paola",
                10,
                "chow",
                "shiba",
                Gender.MALE,
                "Nada",
                30.0,
                Location.BUENOS_AIRES,
                "Agustin",
                false,
                isFavorite = false
            )
        )
        listPet.add(
            Pet(
                "Yanina",
                10,
                "labrador",
                "shiba",
                Gender.MALE,
                "Nada",
                30.0,
                Location.BUENOS_AIRES,
                "Agustin",
                false,
                isFavorite = false
            )
        )
        listPet.add(
            Pet(
                "Camila",
                10,
                "pug",
                "shiba",
                Gender.MALE,
                "Nada",
                30.0,
                Location.BUENOS_AIRES,
                "Agustin",
                false,
                isFavorite = false
            )
        )

        pets.postValue(listPet)
    }

    fun newPet() {
        listPet.add(
            Pet(
                "PRUEBA",
                10,
                "beagle",
                "shiba",
                Gender.MALE,
                "Nada",
                30.0,
                Location.BUENOS_AIRES,
                "Agustin",
                false,
                isFavorite = false
            )
        )

        pets.postValue(listPet)
    }


}