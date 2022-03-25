package com.suonk.tasks_manager.di

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.suonk.tasks_manager.R
import com.suonk.tasks_manager.model.AppDatabase
import com.suonk.tasks_manager.model.dao.ProjectDao
import com.suonk.tasks_manager.model.dao.TaskDao
import com.suonk.tasks_manager.model.data.Project
import com.suonk.tasks_manager.repositories.projects.ProjectRepository
import com.suonk.tasks_manager.repositories.projects.ProjectRepositoryImpl
import com.suonk.tasks_manager.repositories.tasks.TaskRepository
import com.suonk.tasks_manager.repositories.tasks.TaskRepositoryImpl
import com.suonk.tasks_manager.use_case.projects.GetAllProjects
import com.suonk.tasks_manager.use_case.projects.GetProjectById
import com.suonk.tasks_manager.use_case.projects.ProjectUseCases
import com.suonk.tasks_manager.use_case.tasks.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        providerDao: Provider<ProjectDao>
    ) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    CoroutineScope(Dispatchers.IO).launch {
                        prepopulateDatabase(providerDao.get())
                    }
                }

                private suspend fun prepopulateDatabase(projectDao: ProjectDao) {
                    projectDao.insertProject(
                        Project(
                            "Project Tartampion",
                            R.drawable.ic_circle_belge
                        )
                    );
                    projectDao.insertProject(
                        Project(
                            "Project Lucidia",
                            R.drawable.ic_circle_green
                        )
                    );
                    projectDao.insertProject(Project("Project Circus", R.drawable.ic_circle_blue));
                }
            })
            .build()


    @Singleton
    @Provides
    fun provideProjectDao(database: AppDatabase) = database.projectDao()

    @Singleton
    @Provides
    fun provideProjectUseCases(projectRepository: ProjectRepository): ProjectUseCases {
        return ProjectUseCases(
            GetAllProjects(projectRepository),
            GetProjectById(projectRepository)
        )
    }

    @Singleton
    @Provides
    fun provideTaskDao(database: AppDatabase) = database.taskDao()

    @Singleton
    @Provides
    fun provideTaskUseCases(taskRepository: TaskRepository): TaskUseCases {
        return TaskUseCases(
            getAllTasks = GetAllTasks(taskRepository),
            getTaskById = GetTaskById(taskRepository),
            insertTask = InsertTask(taskRepository),
            deleteTask = DeleteTask(taskRepository)
        )
    }
}