package com.proyecto.personal.puppisparcialtp3.data

import com.proyecto.personal.puppisparcialtp3.data.model.DogsModel
import com.proyecto.personal.puppisparcialtp3.data.network.DogsService
import javax.inject.Inject


class DogsRepository  @Inject constructor( private val  service: DogsService){


  suspend fun getDogsFromApi(): DogsModel {
   val response: DogsModel =  service.getDogsImage()
   return response //.map { it.toDomain()}
  }


 }