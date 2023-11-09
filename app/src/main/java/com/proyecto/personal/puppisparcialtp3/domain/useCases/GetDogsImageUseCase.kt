package com.proyecto.personal.puppisparcialtp3.domain.useCases

import android.util.Log
import com.proyecto.personal.puppisparcialtp3.data.DogsRepository
import com.proyecto.personal.puppisparcialtp3.data.model.DogsModel
import javax.inject.Inject

class GetDogsImageUseCase @Inject constructor(private val repository: DogsRepository) {

    suspend operator fun invoke(): DogsModel {
        val dogsImages = repository.getDogsFromApi()
        return if (!dogsImages.dogsImage.isEmpty()) {
            dogsImages

        } else {
            DogsModel(emptyList())
        }
    }
}