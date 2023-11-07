package com.proyecto.personal.puppisparcialtp3.domain.useCases

import com.proyecto.personal.puppisparcialtp3.data.DogsRepository
import com.proyecto.personal.puppisparcialtp3.data.model.DogsModel
import com.proyecto.personal.puppisparcialtp3.domain.Pet
import javax.inject.Inject

class GetSomeDogsUseCase  @Inject
constructor(private val repository: DogsRepository) {

    suspend operator fun invoke(): List<Pet> {
        val someDogs = repository.createDogs()
        return if (!someDogs.isEmpty()) {
            someDogs

        } else {
            emptyList()
        }
    }
}