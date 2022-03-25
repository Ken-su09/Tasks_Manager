package com.suonk.tasks_manager.use_case.projects

data class ProjectUseCases(
    val getAllProjects: GetAllProjects,
    val getProjectById: GetProjectById
) {
}