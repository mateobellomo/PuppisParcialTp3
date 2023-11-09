package com.proyecto.personal.puppisparcialtp3.domain.useCases

import com.proyecto.personal.puppisparcialtp3.data.DogsRepository
import com.proyecto.personal.puppisparcialtp3.data.model.DogsModel
import javax.inject.Inject

class GetAllDogsBreedsUseCase @Inject constructor(private val repository: DogsRepository) {

    suspend operator fun invoke(): List<Pair<String, List<String>>>? {
        val allBreedsList = repository.getAllBreeds()
        if (allBreedsList != null) {
            return allBreedsList
        } else {
            return (emptyList())
        }
    }
}
