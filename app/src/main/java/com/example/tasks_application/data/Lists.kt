package com.example.tasks_application.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "lists_table")
data class Lists(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title : String,
) : Parcelable
