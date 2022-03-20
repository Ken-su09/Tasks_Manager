package com.suonk.tasks_manager.ui.tasks.lists

data class CreateTaskViewState(
    val taskName: String,
    val projectName: String,
    val creationTimestamp: Long
)