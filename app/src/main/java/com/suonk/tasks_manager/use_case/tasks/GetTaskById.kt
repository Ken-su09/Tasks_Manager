package com.suonk.tasks_manager.use_case.tasks

import com.suonk.tasks_manager.model.data.Task
import com.suonk.tasks_manager.repositories.tasks.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTaskById @Inject constructor(private val repository: TaskRepository) {

    operator fun invoke(id: Long): Flow<Task> {
        return repository.getTaskById(id)
    }
}