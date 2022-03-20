package com.suonk.tasks_manager.utils

import com.suonk.tasks_manager.model.data.Task
import com.suonk.tasks_manager.ui.tasks.lists.TasksViewState

sealed class TasksEvent {
    data class Order(val taskOrder: TaskOrder) : TasksEvent()
    data class AddTask(val task: TasksViewState) : TasksEvent()
    data class DeleteTask(val task: Task) : TasksEvent()
    object ToggleOrderSection : TasksEvent()
}
