package com.suonk.tasks_manager.repositories.projects

import com.suonk.tasks_manager.model.data.Project
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {

    fun getAllProjects() : Flow<List<Project>>
    fun getProjectById(id: Long) : Flow<Project>
    suspend fun insertProject(project: Project)
    suspend fun updateProject(project: Project)
    suspend fun deleteProject(project: Project)
}