package com.suonk.tasks_manager.utils

sealed class TasksEvent {
    data class Order(val taskOrder: TaskOrder) : TasksEvent()
    data class DeleteTask(val id: Long) : TasksEvent()
}
