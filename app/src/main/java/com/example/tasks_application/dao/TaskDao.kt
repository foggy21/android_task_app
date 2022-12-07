package com.example.tasks_application.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tasks_application.data.Task

@Dao
abstract class TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun addTask(task: Task)

    @Delete
    abstract fun deleteTask(task: Task)

    @Query("SELECT * FROM task_table JOIN lists_table ON task_table.list_id = lists_table.id")
    abstract fun getTasks() : LiveData<List<Task>>
}