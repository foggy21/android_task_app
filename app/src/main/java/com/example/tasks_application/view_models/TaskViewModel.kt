package com.example.tasks_application.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.example.tasks_application.data.Task
import com.example.tasks_application.database.TaskDatabase
import com.example.tasks_application.fragments.tasks.TasksFragment
import com.example.tasks_application.fragments.tasks.TasksFragmentArgs
import com.example.tasks_application.repositories.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var getTasks: LiveData<List<Task>>
    private val repository : TaskRepository
    private lateinit var args : TasksFragmentArgs

    init{
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
    }

    fun addTask(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            repository.addTask(task)
        }
    }

    fun updateTask(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateTask(task)
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteTask(task)
        }
    }

    fun accessArgs(taskArgs: TasksFragmentArgs){
        args = taskArgs
        getTasks = repository.getTasks(args.currentList.id)
    }
}