package com.suonk.tasks_manager.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.suonk.tasks_manager.model.dao.ProjectDao
import com.suonk.tasks_manager.model.dao.TaskDao
import com.suonk.tasks_manager.model.data.Project
import com.suonk.tasks_manager.model.data.Task

@Database(entities = [Task::class, Project::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
    abstract fun projectDao(): ProjectDao
}