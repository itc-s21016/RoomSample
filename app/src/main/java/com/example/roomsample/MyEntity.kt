package com.example.roomsample

import androidx.annotation.WorkerThread
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_table_name")
@WorkerThread
data class MyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
)
