package com.proyecto.personal.puppisparcialtp3.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proyecto.personal.puppisparcialtp3.entities.Pet

class PetViewModel : ViewModel(){
    val selectedPet = MutableLiveData<Pet>()
}