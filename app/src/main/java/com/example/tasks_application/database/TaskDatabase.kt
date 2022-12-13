package com.example.tasks_application.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tasks_application.dao.ListsDao
import com.example.tasks_application.dao.SubtaskDao
import com.example.tasks_application.dao.TaskDao
import com.example.tasks_application.data.Lists
import com.example.tasks_application.data.Subtask
import com.example.tasks_application.data.Task

@Database(entities = [Lists::class, Task::class, Subtask::class], version = 10, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun listsDao() : ListsDao
    abstract fun taskDao() : TaskDao
    abstract fun subtaskDao() : SubtaskDao

    companion object{
        @Volatile
        private var INSTANCE : TaskDatabase? = null

        fun getDatabase(context: Context) : TaskDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
