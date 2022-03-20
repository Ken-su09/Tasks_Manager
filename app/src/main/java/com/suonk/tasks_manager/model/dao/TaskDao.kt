package com.suonk.tasks_manager.model.dao

import androidx.room.*
import com.suonk.tasks_manager.model.data.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    /**
     *  getAllTasks() = task1, task2, task3....
     */
    @Query("SELECT * FROM task ORDER BY name ASC")
    fun getAllTasks(): Flow<List<Task>>

    /**
     *  getTaskById(id) = task
     */
    @Query("SELECT * FROM task WHERE id == :id")
    fun getTaskById(id: Long): Flow<Task>

    /**
     *  insertTask()
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    /**
     *  updateTask()
     */
    @Update
    suspend fun updateTask(task: Task)

    /**
     *  deleteTask()
     */
    @Delete
    suspend fun deleteTask(task: Task)
}