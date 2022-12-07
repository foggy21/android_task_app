package com.example.tasks_application.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.sql.Date

@Parcelize
@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val task_id: Int,
    val list_id: Int,
    val task_title: String,
    val desc: String,
    val favorite: Boolean,
    val date: String,
) : Parcelable
