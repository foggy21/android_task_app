package com.example.tasks_application.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "subtask_table")
data class Subtask(
    @PrimaryKey(autoGenerate = true)
    val subtask_id : Int,
    val task_id : Int,
    val subtask_title : String,
    val desc: String,
    val date: String,
) : Parcelable
