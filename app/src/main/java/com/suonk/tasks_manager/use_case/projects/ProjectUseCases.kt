package com.suonk.tasks_manager.use_case.projects

import com.suonk.tasks_manager.use_case.tasks.GetAllTasks

data class ProjectUseCases(
    val getAllTasks: GetAllTasks,
    val getProjectById: GetProjectById
) {
}