package com.suonk.tasks_manager.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "project_id")
    val projectId: Long,
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true)
    var id: Long = 0
)
