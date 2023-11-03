package com.proyecto.personal.puppisparcialtp3.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.personal.puppisparcialtp3.domain.GetDogsImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//
//@HiltViewModel
//class HomeViewModel @Inject constructor(
//    private val getDogsImage :GetDogsImageUseCase) : ViewModel() {
//
//
//    val dogsIamges = MutableLiveData<List<String>>()
//
//    fun onCreate() {
//
//        viewModelScope.launch {
//            val result = getDogsImage()
//            Log.d("respuesta", " esta es la respuesta ${result.dogsImage.toString()}")
//
//
//
//        }
//    }
//
//
//
//}