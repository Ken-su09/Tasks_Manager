package com.suonk.tasks_manager.use_case.tasks

import com.suonk.tasks_manager.model.data.InvalidTaskException
import com.suonk.tasks_manager.model.data.Task
import com.suonk.tasks_manager.repositories.tasks.TaskRepository
import com.suonk.tasks_manager.ui.tasks.lists.TasksViewState
import javax.inject.Inject

class InsertTask @Inject constructor(private val repository: TaskRepository) {

    @Throws(InvalidTaskException::class)
    suspend operator fun invoke(tasksViewState: TasksViewState) {
        if (tasksViewState.taskName.isBlank() && tasksViewState.projectName.isBlank()) {
            throw InvalidTaskException("The task name can not be empty.")
        }

        return repository.insertTask(
            Task(
                tasksViewState.taskName,
                tasksViewState.creationTimestamp,
                0
            )
        )
    }
}