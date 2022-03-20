package com.suonk.tasks_manager.use_case.tasks

import com.suonk.tasks_manager.model.data.Task
import com.suonk.tasks_manager.repositories.tasks.TaskRepository
import javax.inject.Inject

class DeleteTask @Inject constructor(private val repository: TaskRepository) {

    suspend operator fun invoke(task: Task) {
        repository.deleteTask(task)
    }
}