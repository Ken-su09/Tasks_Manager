package com.suonk.tasks_manager.di

import com.suonk.tasks_manager.repositories.projects.ProjectRepository
import com.suonk.tasks_manager.repositories.projects.ProjectRepositoryImpl
import com.suonk.tasks_manager.repositories.tasks.TaskRepository
import com.suonk.tasks_manager.repositories.tasks.TaskRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class AppBindings {

    @Binds
    abstract fun bindTaskRepository(impl: TaskRepositoryImpl): TaskRepository

    @Binds
    abstract fun bindProjectRepository(impl: ProjectRepositoryImpl): ProjectRepository
}
