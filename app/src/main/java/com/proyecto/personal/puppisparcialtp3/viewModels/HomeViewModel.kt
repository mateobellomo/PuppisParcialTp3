package com.proyecto.personal.puppisparcialtp3.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class HomeViewModel () : ViewModel() {

    val filters = MutableLiveData<List<String>>()


    fun onCreate() {




        }

    fun addFilter(newFilter: String) {
        val oldList = filters.value?.toMutableList() ?: mutableListOf()
        oldList.add(newFilter)
        Log.d("filters" ,"luego de agregar ${filters.value.toString()}")
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

