package com.proyecto.personal.puppisparcialtp3.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proyecto.personal.puppisparcialtp3.helpers.SharedPref

class ProfileViewModel : ViewModel() {

    private val name: String? = SharedPref.read(SharedPref.NAME, "")

    fun updateImageUrl(imageUrl: String) {
        SharedPref.write(SharedPref.IMAGE_URL, imageUrl)
    }

    fun getName(): String? {
        return this.name
    }
}