package com.proyecto.personal.puppisparcialtp3.listeners

import com.proyecto.personal.puppisparcialtp3.entities.Pet

interface OnViewItemClickedListener {
    fun onViewItemDetail(pet: Pet)

    fun addFavorite(pet: Pet)
}