package com.example.tasks_application.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tasks_application.dao.ListsDao
import com.example.tasks_application.data.Lists

@Database(entities = [Lists::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun listsDao() : ListsDao

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
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
