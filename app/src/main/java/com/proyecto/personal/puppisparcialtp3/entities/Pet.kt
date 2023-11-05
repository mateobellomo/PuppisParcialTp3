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
//    var name: String,
//    var age: Int,
//    var breed: String,
//    var subBreed: String,
//    var gender: String,
//    var description: String?,
//    var weight: String,
//    var location: String,
//    var ownerName: String,
//    var urlImages: MutableList<String>,
//    var isAdopted: Boolean,
//    var isFavorite: Boolean
//): Parcelable {
//    constructor(parcel: Parcel) : this(
//        parcel.readString()!!,
//        parcel.readInt(),
//        parcel.readString()!!,
//        parcel.readString()!!,
//        parcel.readString()!!,
//        parcel.readString(),
//        parcel.readString()!!,
//        parcel.readString()!!,
//        parcel.readString()!!,
//        mutableListOf<String>().apply {
//            addAll(parcel.createStringArrayList() ?: emptyList<String>())
//        },
//        parcel.readInt() != 0,
//        parcel.readInt() != 0
//    )
//
//    class Gender {
//        companion object{
//            val FEMALE = "Female"
//            val MALE = "Male"
//        }
//    }
//
//    class Location {
//        companion object{
//            val BUENOS_AIRES = "Buenos Aires"
//            val CIUDAD_AUTONOMA_DE_BUENOS_AIRES = "Ciudad Autónoma de Buenos Aires"
//            val CATAMARCA = "Catamarca"
//            val CHACO = "Chaco"
//            val CHUBUT = "Chubut"
//            val CORDOBA = "Córdoba"
//            val CORRIENTES = "Corrientes"
//            val ENTRE_RIOS = "Entre Ríos"
//            val FORMOSA = "Formosa"
//            val JUJUY = "Jujuy"
//            val LA_PAMPA = "La Pampa"
//            val LA_RIOJA = "La Rioja"
//            val MENDOZA = "Mendoza"
//            val MISIONES = "Misiones"
//            val NEUQUEN = "Neuquén"
//            val RIO_NEGRO = "Río Negro"
//            val SALTA = "Salta"
//            val SAN_JUAN = "San Juan"
//            val SAN_LUIS = "San Luis"
//            val SANTA_CRUZ = "Santa Cruz"
//            val SANTA_FE = "Santa Fe"
//            val SANTIAGO_DEL_ESTERO = "Santiago del Estero"
//            val TIERRA_DEL_FUEGO = "Tierra del Fuego, Antártida e Islas del Atlántico Sur"
//            val TUCUMAN = "Tucumán"
//        }
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeString(name)
//        parcel.writeInt(age)
//        parcel.writeString(breed)
//        parcel.writeString(subBreed)
//        parcel.writeString(gender)
//        parcel.writeString(description)
//        parcel.writeString(weight)
//        parcel.writeString(location)
//        parcel.writeString(ownerName)
//        parcel.writeStringList(urlImages)
//        parcel.writeInt(if (isAdopted) 1 else 0)
//        parcel.writeInt(if (isFavorite) 1 else 0)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<Pet> {
//        override fun createFromParcel(parcel: Parcel): Pet {
//            return Pet(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Pet?> {
//            return arrayOfNulls(size)
//        }
//    }
//
//}