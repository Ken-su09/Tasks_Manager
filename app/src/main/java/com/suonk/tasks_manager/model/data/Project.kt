package com.suonk.tasks_manager.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "project")
data class Project(
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "color")
    val color: Int,
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true)
    var id: Long = 0
)
