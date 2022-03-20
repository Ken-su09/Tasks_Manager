package com.suonk.tasks_manager.repositories.tasks

import com.suonk.tasks_manager.model.dao.TaskDao
import com.suonk.tasks_manager.model.data.Task
import com.suonk.tasks_manager.repositories.tasks.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val dao: TaskDao) : TaskRepository {

    override fun getAllTasks() = dao.getAllTasks()

    override fun getTaskById(id: Long) = dao.getTaskById(id)

    override suspend fun insertTask(task: Task) = dao.insertTask(task)
    override suspend fun updateTask(task: Task) = dao.updateTask(task)
    override suspend fun deleteTask(task: Task) = dao.deleteTask(task)
}