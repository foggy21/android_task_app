package com.example.tasks_application.repositories

import androidx.lifecycle.LiveData
import com.example.tasks_application.dao.ListsDao
import com.example.tasks_application.data.Lists

class ListsRepository(private val listsDao : ListsDao) {
    val getLists: LiveData<List<Lists>> = listsDao.getLists()

    suspend fun addList(list : Lists){
        listsDao.addList(list)
    }

    suspend fun updateList(list: Lists){
        listsDao.updateList(list)
    }

    suspend fun deleteList(list: Lists){
        listsDao.deleteList(list)
    }
}