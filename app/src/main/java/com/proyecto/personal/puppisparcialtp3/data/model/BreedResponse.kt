package com.proyecto.personal.puppisparcialtp3.data.model

data class BreedResponse(
    val message: Map<String, List<String>>, // Un mapa de nombre de raza a lista de subrazas
    val status: String
)