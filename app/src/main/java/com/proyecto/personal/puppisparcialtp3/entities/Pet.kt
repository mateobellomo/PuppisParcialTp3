package com.proyecto.personal.puppisparcialtp3.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pets")
class Pet(
    name: String,
    age: String,
    breed: String,
    subBreed: String?,
    gender: Gender,
    description: String?,
    weight: String,
    location: Location,
    ownerName: String,
    isAdopted: Boolean,
    isFavorite: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "name")
    var name: String

    @ColumnInfo(name = "age")
    var age: String

    @ColumnInfo(name = "breed")
    var breed: String

    @ColumnInfo(name = "subBreed")
    var subBreed: String?

    @ColumnInfo(name = "gender")
    var gender: Gender

    @ColumnInfo(name = "description")
    var description: String?

    @ColumnInfo(name = "weight")
    var weight: String

    @ColumnInfo(name = "location")
    var location: Location

    @ColumnInfo(name = "ownerName")
    var ownerName: String

    @ColumnInfo(name = "isAdopted")
    var isAdopted: Boolean

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean

    init {
        this.name = name
        this.age = age
        this.breed = breed
        this.subBreed = subBreed
        this.gender = gender
        this.description = description
        this.weight = weight
        this.location = location
        this.ownerName = ownerName
        this.isAdopted = isAdopted
        this.isFavorite = isFavorite
    }
}