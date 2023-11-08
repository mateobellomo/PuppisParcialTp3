package com.proyecto.personal.puppisparcialtp3.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.proyecto.personal.puppisparcialtp3.data.model.PetEntity

@Dao
interface PetsDAO{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPet(petEntity: PetEntity?)

//    @Update
//    fun updatePet(pet: Pet?)

    @Query("UPDATE pets SET isFavorite = :isFavorite WHERE id = :id")
    fun updateIsFavorite(isFavorite: Boolean, id: Int)

    @Query("UPDATE pets SET isAdopted = :isAdopted WHERE id = :id")
    fun updateIsAdopted(isAdopted: Boolean, id: Int)

    @Query("SELECT * FROM pets WHERE isAdopted = 0")
    fun loadAllAvailablePets(): MutableList<PetEntity?>?

    @Query("SELECT * FROM pets WHERE isFavorite = 1")
    fun loadFavoritesPets(): MutableList<PetEntity?>?

//    @Transaction
//    @Query("SELECT * FROM images WHERE imageId = :id")
//    fun getPetImages(id: Int): List<PetWithImages>
}