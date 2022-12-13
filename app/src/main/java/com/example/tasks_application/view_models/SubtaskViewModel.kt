package com.example.tasks_application.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.tasks_application.data.Subtask
import com.example.tasks_application.database.TaskDatabase
import com.example.tasks_application.fragments.subtasks.SubtaskFragmentArgs
import com.example.tasks_application.repositories.SubtaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubtaskViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var getSubtasks: LiveData<List<Subtask>>
    private val repository: SubtaskRepository
    private lateinit var args : SubtaskFragmentArgs

    init {
        val subtaskDao = TaskDatabase.getDatabase(application).subtaskDao()
        repository = SubtaskRepository(subtaskDao)
    }

    fun addSubtask(subtask: Subtask) {
        viewModelScope.launch(Dispatchers.IO){
            repository.addSubtask(subtask)
        }
    }

    fun deleteSubtask(subtask: Subtask) {
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteSubtask(subtask)
        }
    }

    fun updateSubtask(subtask: Subtask){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateSubtask(subtask)
        }
    }

    fun accessArgs(subtaskArgs: SubtaskFragmentArgs){
        args = subtaskArgs
        getSubtasks = repository.getSubtasks(args.currentTask.task_id)
    }
}