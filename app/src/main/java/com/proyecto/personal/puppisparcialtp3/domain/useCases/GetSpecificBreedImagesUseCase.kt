package com.proyecto.personal.puppisparcialtp3.domain.useCases

import com.proyecto.personal.puppisparcialtp3.data.DogsRepository
import com.proyecto.personal.puppisparcialtp3.data.model.DogsModel
import javax.inject.Inject

class GetSpecificBreedImagesUseCase @Inject constructor(private val repository: DogsRepository) {

    suspend operator fun invoke(breed: String, imgNumber: Int): DogsModel {
        val dogsImages = repository.getSpecificBreedImages(breed, imgNumber)
        return if (!dogsImages.dogsImage.isEmpty()) {
            dogsImages

        } else {
            DogsModel(emptyList())
        }
    }
}