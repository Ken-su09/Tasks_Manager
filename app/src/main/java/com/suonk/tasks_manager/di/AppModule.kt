package com.suonk.tasks_manager.di

import android.content.Context
import androidx.room.Room
import com.suonk.tasks_manager.model.AppDatabase
import com.suonk.tasks_manager.model.dao.ProjectDao
import com.suonk.tasks_manager.model.dao.TaskDao
import com.suonk.tasks_manager.repositories.projects.ProjectRepositoryImpl
import com.suonk.tasks_manager.repositories.tasks.TaskRepository
import com.suonk.tasks_manager.repositories.tasks.TaskRepositoryImpl
import com.suonk.tasks_manager.use_case.tasks.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        )
            .allowMainThreadQueries()
            .addMigrations()
            .build()


    @Provides
    fun provideProjectDao(database: AppDatabase) = database.projectDao()

    @Provides
    fun provideProjectRepository(dao: ProjectDao) = ProjectRepositoryImpl(dao)

    @Provides
    fun provideTaskDao(database: AppDatabase) = database.taskDao()

    @Provides
    fun provideTaskRepository(dao: TaskDao): TaskRepository = TaskRepositoryImpl(dao)

    @Provides
    fun provideTaskUseCases(repository: TaskRepository): TaskUseCases {
        return TaskUseCases(
            getAllTasks = GetAllTasks(repository),
            getTaskById = GetTaskById(repository),
            insertTask = InsertTask(repository),
            deleteTask = DeleteTask(repository)
        )
    }
}