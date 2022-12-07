package com.example.tasks_application.repositories

import androidx.lifecycle.LiveData
import com.example.tasks_application.dao.TaskDao
import com.example.tasks_application.data.Task

class TaskRepository(private val taskDao: TaskDao) {
    val getTasks : LiveData<List<Task>> = taskDao.getTasks()

    suspend fun addTask(task: Task){
        taskDao.addTask(task)
    }

    suspend fun deleteTask(task: Task){
        taskDao.deleteTask(task)
    }
}