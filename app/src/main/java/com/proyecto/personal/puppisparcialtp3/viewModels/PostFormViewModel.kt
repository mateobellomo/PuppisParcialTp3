package com.proyecto.personal.puppisparcialtp3.viewModels


import androidx.lifecycle.ViewModel


class PostFormViewModel : ViewModel() {

    val dogImages: MutableList<String> = ArrayList()


    fun saveImage(url: String) {
        dogImages.add(url)
    }

    fun removeImage(url: String) {
        dogImages.remove(url)
    }

    fun getall(): List<String> {
        return dogImages
    }

}