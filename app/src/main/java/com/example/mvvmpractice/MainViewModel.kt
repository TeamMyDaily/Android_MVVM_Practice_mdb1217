package com.example.mvvmpractice

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class MainViewModel(application:Application) : AndroidViewModel(application) {
    val repository = ListRepository(application)
    val lists = repository.getAll()

    fun getAll(): LiveData<List<ListData>> {
        return lists
    }

    fun insert(l : ListData) {
        repository.insert(l)
    }

    fun update(l : ListData) {
        repository.update(l)
    }

    fun delete(l : ListData) {
        repository.delete(l)
    }
}