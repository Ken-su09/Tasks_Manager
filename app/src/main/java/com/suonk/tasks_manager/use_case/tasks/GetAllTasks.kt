package com.suonk.tasks_manager.use_case.tasks

import com.suonk.tasks_manager.model.data.Task
import com.suonk.tasks_manager.repositories.tasks.TaskRepository
import com.suonk.tasks_manager.utils.OrderType
import com.suonk.tasks_manager.utils.TaskOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllTasks @Inject constructor(private val repository: TaskRepository) {

    operator fun invoke(
        taskOrder: TaskOrder = TaskOrder.Name(OrderType.Ascending)
    ): Flow<List<Task>> {
        return repository.getAllTasks().map { tasks ->
            when (taskOrder.orderType) {
                is OrderType.Ascending -> {
                    when (taskOrder) {
                        is TaskOrder.Name -> tasks.sortedBy { it.name }
                        is TaskOrder.Color -> tasks.sortedBy { it.projectId }
                    }
                }
                is OrderType.Descending -> {
                    when (taskOrder) {
                        is TaskOrder.Name -> tasks.sortedByDescending { it.name }
                        is TaskOrder.Color -> tasks.sortedByDescending { it.projectId }
                    }
                }
            }
        }
    }
}