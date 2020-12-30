package com.example.mvvmpractice

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "list_data_table")
data class ListData(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "number")
    var content: String,

    @ColumnInfo(name = "label")
    var label: String
){
    constructor() : this(null, "", "", "")
}
