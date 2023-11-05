package com.proyecto.personal.puppisparcialtp3.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "images")
class Image(url: String, petId: Int) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "imageId")
    var imageId: Int = 0

    @ColumnInfo(name = "url")
    var url: String

    @ColumnInfo(name = "petId")
    var petId: Int

    init {
        this.url = url
        this.petId = petId
    }
}

data class PetWithImages(
    @Embedded var pet: Pet,
    @Relation(
        parentColumn = "id",
        entityColumn = "imageId"
    )
    var images: List<Image>
)