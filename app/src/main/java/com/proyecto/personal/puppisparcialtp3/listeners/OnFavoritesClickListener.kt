package com.proyecto.personal.puppisparcialtp3.listeners

import com.proyecto.personal.puppisparcialtp3.domain.Pet

interface OnFavoritesClickListener  {
    fun onFavoritesClick(pet: Pet)
}