package com.example.roomsample

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MyViewModel(private val repository: MyRepository) : ViewModel() {
    val allEntities: LiveData<List<MyEntity>> = repository.getAllEntities()
    fun insertt(entity: MyEntity) {
        viewModelScope.launch {
            repository.inserttt(entity)
        }
    }
}
class MyViewModelFactory(private val repository: MyRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MyViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}