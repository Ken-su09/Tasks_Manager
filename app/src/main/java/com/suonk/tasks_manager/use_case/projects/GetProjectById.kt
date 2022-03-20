package com.suonk.tasks_manager.use_case.projects

import com.suonk.tasks_manager.model.data.Project
import com.suonk.tasks_manager.repositories.projects.ProjectRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProjectById @Inject constructor(private val repository: ProjectRepository) {

    operator fun invoke(id: Long): Flow<Project> {
        return repository.getProjectById(id)
    }
}