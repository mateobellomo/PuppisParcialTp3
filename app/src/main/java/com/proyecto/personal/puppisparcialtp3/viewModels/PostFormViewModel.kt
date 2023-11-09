package com.proyecto.personal.puppisparcialtp3.viewModels


import androidx.lifecycle.ViewModel


class PostFormViewModel : ViewModel() {

    private val dogImages: MutableList<String> = ArrayList()

    fun saveImage(url: String) {
        dogImages.add(url)
    }

    fun removeImage(url: String) {
        dogImages.remove(url)
    }

    fun getAll(): List<String> {
        return dogImages
    }
}