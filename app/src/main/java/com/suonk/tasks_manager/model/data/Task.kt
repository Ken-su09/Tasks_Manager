package com.suonk.tasks_manager.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "creationTimestamp")
    val creationTimestamp: Long,
    @ColumnInfo(name = "projectId")
    val projectId: Long,
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)

class InvalidTaskException(message: String): Exception(message)
