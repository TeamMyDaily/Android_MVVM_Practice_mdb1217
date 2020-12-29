package com.example.mvvmpractice

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ListDao {
    @Query("SELECT * FROM list_data_table")
    fun getAll(): LiveData<List<ListData>>

    @Insert
    fun insert(listdata: ListData)

    @Update
    fun update(listdata: ListData)

    @Delete
    fun delete(listdata: ListData)
}