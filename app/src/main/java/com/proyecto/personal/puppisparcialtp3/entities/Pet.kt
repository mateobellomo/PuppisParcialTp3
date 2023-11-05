package com.proyecto.personal.puppisparcialtp3.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pets")
class Pet(
    name: String,
    age: Int,
    breed: String,
    subBreed: String?,
    gender: Gender,
    description: String?,
    weight: Double,
    location: Location,
    ownerName: String,
    urlImages: MutableList<String>,
    isAdopted: Boolean,
    isFavorite: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0

    @ColumnInfo(name = "name")
    var name: String = name

    @ColumnInfo(name = "age")
    var age: Int = age

    @ColumnInfo(name = "breed")
    var breed: String = breed

    @ColumnInfo(name = "subBreed")
    var subBreed: String? = subBreed

    @ColumnInfo(name = "gender")
    var gender: Gender = gender

    @ColumnInfo(name = "description")
    var description: String? = description

    @ColumnInfo(name = "weight")
    var weight: Double = weight

    @ColumnInfo(name = "location")
    var location: Location = location

    @ColumnInfo(name = "ownerName")
    var ownerName: String = ownerName

    @ColumnInfo(name = "urlImages")
    var urlImages: MutableList<String> = urlImages

    @ColumnInfo(name = "isAdopted")
    var isAdopted: Boolean = isAdopted

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = isFavorite
}