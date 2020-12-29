package com.example.mvvmpractice

import android.app.Application
import androidx.lifecycle.LiveData

class ListRepository(application: Application) {
    val listDatabase = ListDatabase.getInstance(application)!!
    val listDao: ListDao = listDatabase.listDao()
    val lists: LiveData<List<ListData>> = listDao.getAll()

    fun getAll(): LiveData<List<ListData>> {
        return lists
    }

    fun insert(l: ListData) {
        try {
            val thread = Thread(Runnable {
                listDao.insert(l)
            })
            thread.start()
        } catch(e: Exception) { }
    }

    fun update(l : ListData) {
        try {
            val thread = Thread(Runnable {
                listDao.update(l)
            })
            thread.start()
        } catch(e: Exception) { }
    }

    fun delete(l : ListData) {
        try {
            val thread = Thread(Runnable {
                listDao.delete(l)
            })
            thread.start()
        } catch(e: Exception) { }
    }
}