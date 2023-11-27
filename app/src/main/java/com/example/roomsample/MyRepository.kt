package com.example.roomsample

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class MyRepository(private val myDao: MyDao) {
    fun getAllEntities(): LiveData<List<MyEntity>> {
        return myDao.getAll()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun inserttt(entity: MyEntity) {
        myDao.Insert(entity)
    }
}