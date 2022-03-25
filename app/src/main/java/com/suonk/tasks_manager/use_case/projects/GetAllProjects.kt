package com.suonk.tasks_manager.use_case.projects

import com.suonk.tasks_manager.model.data.Project
import com.suonk.tasks_manager.repositories.projects.ProjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllProjects @Inject constructor(private val repository: ProjectRepository) {

    operator fun invoke(): Flow<List<Project>> {
        return repository.getAllProjects()
    }
}