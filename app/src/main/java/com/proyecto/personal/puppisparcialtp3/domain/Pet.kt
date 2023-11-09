package com.proyecto.personal.puppisparcialtp3.domain

import com.proyecto.personal.puppisparcialtp3.utils.Gender
import com.proyecto.personal.puppisparcialtp3.utils.Location

data class Pet(
    val id: Long,
    val name: String,
    val age: Int,
    val breed: String,
    val subBreed: String?,
    val gender: Gender,
    val description: String?,
    val weight: String,
    val location: Location,
    val ownerName: String,
    var photo: List<String>?,
    var isAdopted: Boolean,
    var isFavorite: Boolean,
    var ownerNumber: String
)
{
    companion object {
        private var nextId = 0L

        fun nextId(): Long {
            return nextId++
        }
    }
}
