package com.proyecto.personal.puppisparcialtp3.data.network

import com.proyecto.personal.puppisparcialtp3.data.model.DogsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DogsService @Inject constructor(private val service: DogsApiClient) {

    suspend fun getDogsImage(): DogsModel {
        return withContext(Dispatchers.IO) {
            val response = service.getDogsImageFromApi()
            val result = response.body()?.message ?: emptyList()

            DogsModel(result)
        }
    }

    suspend fun getDogsBreeds(): List<Pair<String, List<String>>>? {
        val response = service.getAllBreedsFromApi()
        val breedResponse = response.body()

        return breedResponse?.message?.toList()
    }

    suspend fun getSpecificBreedImages(breed: String, imgNumber: Int): DogsModel {
        return withContext(Dispatchers.IO) {
            val response = service.getRamdomImageBreedFromApi(breed, imgNumber)
            val result = response.body()?.message ?: emptyList()

            DogsModel(result)
        }
    }
}