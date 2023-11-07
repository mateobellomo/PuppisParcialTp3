package com.proyecto.personal.puppisparcialtp3.listeners

import com.proyecto.personal.puppisparcialtp3.data.model.PetEntity
import com.proyecto.personal.puppisparcialtp3.domain.Pet

interface OnViewItemClickedListener {
    fun onViewItemDetail(pet: Pet)
}