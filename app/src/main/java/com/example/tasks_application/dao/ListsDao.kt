package com.example.tasks_application.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tasks_application.data.Lists

@Dao
abstract class ListsDao  {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addList(list: Lists)

    @Update
    abstract suspend fun updateList(list : Lists)

    @Delete
    abstract suspend fun  deleteList(list : Lists)

    @Query("SELECT * FROM lists_table ORDER BY id ASC")
    abstract fun getLists() : LiveData<List<Lists>>
}