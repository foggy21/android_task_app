package com.example.tasks_application.repositories

import androidx.lifecycle.LiveData
import com.example.tasks_application.dao.TaskDao
import com.example.tasks_application.data.Task

class TaskRepository(private val taskDao: TaskDao) {
    fun getTasks(id: Int) : LiveData<List<Task>>{
        return taskDao.getTasks(id)
    }

    suspend fun addTask(task: Task){
        taskDao.addTask(task)
    }

    suspend fun updateTask(task: Task){
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task){
        taskDao.deleteTask(task)
    }
}