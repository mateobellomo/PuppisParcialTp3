package com.proyecto.personal.puppisparcialtp3.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PostFormViewModel : ViewModel() {

    val dogImages = MutableLiveData<List<String>>()

    fun saveImage(url: String) {
        val currentList = dogImages.value ?: emptyList()
        val updatedList = currentList.toMutableList()
        updatedList.add(url)
        dogImages.value = updatedList
    }

    fun removeImage(url: String) {
        val currentList = dogImages.value ?: emptyList()
        val updatedList = currentList.toMutableList()
        updatedList.remove(url)
        dogImages.value = updatedList
    }
}