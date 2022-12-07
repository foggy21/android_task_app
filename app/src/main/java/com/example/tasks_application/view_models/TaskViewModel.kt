package com.example.tasks_application.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.tasks_application.data.Task
import com.example.tasks_application.database.TaskDatabase
import com.example.tasks_application.repositories.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    val getTasks: LiveData<List<Task>>
    private val repository : TaskRepository

    init{
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
        getTasks = repository.getTasks
    }

    fun addTask(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            repository.addTask(task)
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteTask(task)
        }
    }
}