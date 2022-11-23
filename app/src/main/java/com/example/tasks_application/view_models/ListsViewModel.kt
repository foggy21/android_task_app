package com.example.tasks_application.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.tasks_application.data.Lists
import com.example.tasks_application.database.TaskDatabase
import com.example.tasks_application.repositories.ListsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListsViewModel(application : Application) : AndroidViewModel(application) {
    val getLists : LiveData<List<Lists>>
    private val repository : ListsRepository

    init {
        val listsDao = TaskDatabase.getDatabase(application).listsDao()
        repository = ListsRepository(listsDao)
        getLists = repository.getLists
    }

    fun addList(list: Lists){
        viewModelScope.launch(Dispatchers.IO){
            repository.addList(list)
        }
    }

    fun deleteList(list: Lists){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteList(list)
        }
    }
}