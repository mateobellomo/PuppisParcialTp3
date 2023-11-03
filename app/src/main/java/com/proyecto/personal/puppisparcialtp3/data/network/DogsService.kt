package com.proyecto.personal.puppisparcialtp3.data.network

import com.proyecto.personal.puppisparcialtp3.data.model.DogsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DogsService   @Inject constructor(private val service : DogsApiClient ) {

    suspend fun getDogsImage(): DogsModel {
        return withContext(Dispatchers.IO) {
            val response = service.getDogsImageFromApi()

            response.body() ?: DogsModel(emptyList())
        }
    }
}