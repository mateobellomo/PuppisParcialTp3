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


class HomeViewModel() : ViewModel() {

    val filters = MutableLiveData<List<String>>()

    fun addFilter(newFilter: String) {
        val oldList = filters.value?.toMutableList() ?: mutableListOf()
        oldList.add(newFilter)
        Log.d("filters", "luego de agregar ${filters.value.toString()}")
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
}

