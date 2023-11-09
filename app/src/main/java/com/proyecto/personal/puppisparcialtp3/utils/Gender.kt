package com.proyecto.personal.puppisparcialtp3.utils

enum class Gender(gender: String) {
    FEMALE("Female"),
    MALE("Male");

    companion object {
        fun fromString(value: String): Gender {
            return when (value) {
                "Male" -> MALE
                else -> FEMALE
            }
        }
    }
}