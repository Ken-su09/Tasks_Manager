package com.suonk.tasks_manager.repositories.tasks

import com.suonk.tasks_manager.model.data.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository  {
    fun getAllTasks() : Flow<List<Task>>
    fun getTaskById(id: Long) : Flow<Task>
    suspend fun insertTask(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(task: Task)
}