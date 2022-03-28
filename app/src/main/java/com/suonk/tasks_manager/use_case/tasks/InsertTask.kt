package com.suonk.tasks_manager.use_case.tasks

import android.util.Log
import com.suonk.tasks_manager.model.data.Task
import com.suonk.tasks_manager.repositories.tasks.TaskRepository
import com.suonk.tasks_manager.ui.tasks.create.CreateTaskViewState
import javax.inject.Inject

class InsertTask @Inject constructor(private val taskRepository: TaskRepository) {

    suspend operator fun invoke(createTaskViewState: CreateTaskViewState) {
        val projectId = when (createTaskViewState.projectName) {
            "Project Tartampion" -> {
                1L
            }
            "Project Lucidia" -> {
                2L
            }
            "Project Circus" -> {
                3L
            }
            else -> {
                1L
            }
        }

        taskRepository.insertTask(
            Task(
                createTaskViewState.taskName,
                projectId
            )
        )
    }
}