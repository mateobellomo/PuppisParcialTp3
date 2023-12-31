package com.proyecto.personal.puppisparcialtp3.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.proyecto.personal.puppisparcialtp3.data.model.PetEntity

@Database(entities = [PetEntity::class], version = 3, exportSchema = false)
abstract class appDatabase : RoomDatabase() {

    abstract fun petsDAO(): PetsDAO

    companion object {

        var INSTANCE: appDatabase? = null

        fun getAppDataBase(context: Context): appDatabase? {
            if (INSTANCE == null) {
                synchronized(appDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        appDatabase::class.java,
                        "petsDB"
                    ).addMigrations().allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}