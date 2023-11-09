package com.proyecto.personal.puppisparcialtp3.data.network

import com.proyecto.personal.puppisparcialtp3.data.model.BreedResponse
import com.proyecto.personal.puppisparcialtp3.data.model.DogsModel
import com.proyecto.personal.puppisparcialtp3.data.model.PaginateResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogsApiClient {
    @GET("/api/breeds/image/random/8")
    suspend fun getDogsImageFromApi(): Response<PaginateResponse<String>>

    @GET("/api/breeds/list/all")
    suspend fun getAllBreedsFromApi(): Response<BreedResponse>

    @GET("/api/breed/{breed}/images/random/{imageNumber}")
    suspend fun getRamdomImageBreedFromApi(
        @Path("breed") breed: String,
        @Path("imageNumber") imageNumber: Int,
    ): Response<PaginateResponse<String>>
}