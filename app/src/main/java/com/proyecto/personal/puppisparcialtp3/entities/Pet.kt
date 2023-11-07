package com.proyecto.personal.puppisparcialtp3.entities

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi

data class Pet (
    var name: String,
    var age: String,
    var breed: String,
    var subBreed: String,
    var gender: String,
    var description: String?,
    var weight: String,
    var location: String,
    var ownerName: String,
    var urlImages: MutableList<String>,
    var isAdopted: Boolean,
    var isFavorite: Boolean


): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        mutableListOf<String>().apply {
            addAll(parcel.createStringArrayList() ?: emptyList<String>())
        },
        parcel.readInt() != 0,
        parcel.readInt() != 0
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(age)
        parcel.writeString(breed)
        parcel.writeString(subBreed)
        parcel.writeString(gender)
        parcel.writeString(description)
        parcel.writeString(weight)
        parcel.writeString(location)
        parcel.writeString(ownerName)
        parcel.writeStringList(urlImages)
        parcel.writeString(isAdopted.toString())
        parcel.writeString(isFavorite.toString())
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pet> {
        override fun createFromParcel(parcel: Parcel): Pet {
            return Pet(parcel)
        }

        override fun newArray(size: Int): Array<Pet?> {
            return arrayOfNulls(size)
        }
    }

}