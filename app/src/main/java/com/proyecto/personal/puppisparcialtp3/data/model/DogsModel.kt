package com.proyecto.personal.puppisparcialtp3.data.model

import com.google.gson.annotations.SerializedName

data class DogsModel (
    @SerializedName("message") val dogsImage: List<String>
)