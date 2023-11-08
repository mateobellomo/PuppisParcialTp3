package com.proyecto.personal.puppisparcialtp3.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proyecto.personal.puppisparcialtp3.helpers.SharedPref

class ProfileViewModel: ViewModel() {

    val _imageUrl = MutableLiveData<String?>()

    private val name: String? = SharedPref.read(SharedPref.NAME, null)
    private val imageUrl: String? = SharedPref.read(SharedPref.IMAGE_URL, null)

    fun onCreate() {
        if (!this.imageUrl.isNullOrEmpty()) {
            _imageUrl.postValue(this.imageUrl)
        }
    }

    fun updateImageUrl(imageUrl: String) {
        SharedPref.write(SharedPref.IMAGE_URL, imageUrl)
        _imageUrl.postValue(imageUrl)
    }

    fun getName(): String? {
        return this.name
    }
}