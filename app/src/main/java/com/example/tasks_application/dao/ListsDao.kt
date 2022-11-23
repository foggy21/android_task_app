package com.example.tasks_application.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tasks_application.data.Lists

@Dao
abstract class ListsDao  {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun addList(list: Lists)

    @Delete
    abstract suspend fun  deleteList(list : Lists)

    @Query("SELECT * FROM lists_table ORDER BY id ASC")
    abstract fun getLists() : LiveData<List<Lists>>
}