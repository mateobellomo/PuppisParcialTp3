package com.proyecto.personal.puppisparcialtp3.data.network

import com.proyecto.personal.puppisparcialtp3.data.model.DogsModel
import retrofit2.Response
import retrofit2.http.GET

interface DogsApiClient {
    @GET("/breeds/image/random/3")
    suspend fun getDogsImageFromApi(): Response<DogsModel>
}