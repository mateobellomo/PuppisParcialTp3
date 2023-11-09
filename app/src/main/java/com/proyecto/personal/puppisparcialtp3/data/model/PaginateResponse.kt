package com.proyecto.personal.puppisparcialtp3.data.model

data class  PaginateResponse  <T>(// la data class es similar a un enum,
    // es una lista que no cambia. Va a quedar asi
    val message: List<T>,
    val status:String
)