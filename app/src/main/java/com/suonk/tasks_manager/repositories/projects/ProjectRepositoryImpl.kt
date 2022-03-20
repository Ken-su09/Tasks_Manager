package com.suonk.tasks_manager.repositories.projects

import com.suonk.tasks_manager.model.dao.ProjectDao
import com.suonk.tasks_manager.model.data.Project
import com.suonk.tasks_manager.repositories.projects.ProjectRepository
import javax.inject.Inject

class ProjectRepositoryImpl @Inject constructor(private val dao: ProjectDao) : ProjectRepository {

    override fun getAllProjects() = dao.getAllProjects()
    override fun getProjectById(id: Long) = dao.getProjectById(id)

    override suspend fun insertProject(project: Project) = dao.insertProject(project)
    override suspend fun updateProject(project: Project) = dao.updateProject(project)
    override suspend fun deleteProject(project: Project) = dao.deleteProject(project)
}