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




        }
    }

