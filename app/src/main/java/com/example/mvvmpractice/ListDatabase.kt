package com.example.mvvmpractice

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ListData::class], version = 1)
abstract class ListDatabase: RoomDatabase() {
    abstract fun listDao(): ListDao

    companion object {
        var INSTANCE: ListDatabase? = null

        fun getInstance(context: Context): ListDatabase? {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ListDatabase::class.java,
                        "list database")
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return INSTANCE
            }
        }
    }
}