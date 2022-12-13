package com.example.tasks_application.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.tasks_application.data.Lists
import com.example.tasks_application.data.Task

@Dao
abstract class TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addTask(task: Task)

    @Delete
    abstract suspend fun deleteTask(task: Task)

    @Update
    abstract suspend fun updateTask(task: Task)

    @Query("SELECT * FROM task_table JOIN lists_table ON lists_table.id = :id and lists_table.id = task_table.list_id")
    abstract fun getTasks(id : Int) : LiveData<List<Task>>
}