package com.suonk.tasks_manager.use_case.tasks

data class TaskUseCases (
    val getAllTasks: GetAllTasks,
    val getTaskById: GetTaskById,
    val insertTask: InsertTask,
    val deleteTask: DeleteTask
)