package com.example.tasks_application.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.tasks_application.data.Subtask

@Dao
abstract class SubtaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addSubtask(subtask: Subtask)

    @Update
    abstract suspend fun updateSubtask(subtask: Subtask)

    @Delete
    abstract suspend fun deleteSubtask(subtask: Subtask)

    @Query("SELECT * FROM subtask_table JOIN task_table ON task_table.task_id = :id and task_table.task_id = subtask_table.task_id")
    abstract fun getSubtasks(id: Int) : LiveData<List<Subtask>>
}