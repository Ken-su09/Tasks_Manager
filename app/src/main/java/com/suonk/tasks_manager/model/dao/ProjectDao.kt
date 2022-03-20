package com.suonk.tasks_manager.model.dao

import androidx.room.*
import com.suonk.tasks_manager.model.data.Project
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {

    /**
     *  getAllProjects() = project1, project2, project3....
     */
    @Query("SELECT * FROM project ORDER BY name ASC")
    fun getAllProjects(): Flow<List<Project>>

    /**
     *  getProjectById(id) = project
     */
    @Query("SELECT * FROM project WHERE id == :id")
    fun getProjectById(id: Long): Flow<Project>

    /**
     *  insertProject()
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(project: Project)

    /**
     *  updateProject()
     */
    @Update
    suspend fun updateProject(project: Project)

    /**
     *  deleteProject()
     */
    @Delete
    suspend fun deleteProject(project: Project)
}