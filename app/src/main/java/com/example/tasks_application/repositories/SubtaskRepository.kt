package com.example.tasks_application.repositories

import androidx.lifecycle.LiveData
import com.example.tasks_application.dao.SubtaskDao
import com.example.tasks_application.data.Subtask

class SubtaskRepository(private val subtaskDao: SubtaskDao) {
    fun getSubtasks(id : Int) : LiveData<List<Subtask>> {
        return subtaskDao.getSubtasks(id)
    }

    suspend fun addSubtask(subtask: Subtask) {
        subtaskDao.addSubtask(subtask)
    }

    suspend fun deleteSubtask(subtask: Subtask) {
        subtaskDao.deleteSubtask(subtask)
    }

    suspend fun updateSubtask(subtask: Subtask) {
        subtaskDao.updateSubtask(subtask)
    }
}