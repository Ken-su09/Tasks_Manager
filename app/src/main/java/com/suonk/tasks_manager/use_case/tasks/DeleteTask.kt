package com.suonk.tasks_manager.use_case.tasks

import com.suonk.tasks_manager.model.data.Task
import com.suonk.tasks_manager.repositories.tasks.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


class DeleteTask @Inject constructor(private val repository: TaskRepository) {

    suspend operator fun invoke(id: Long) {
        repository.getAllTasks().collect { tasks ->
            val iterator = tasks.toMutableList().iterator()
            while (iterator.hasNext()) {
                val task: Task = iterator.next()
                if (task.id == id) {
                    CoroutineScope(Dispatchers.IO).launch {
                        repository.deleteTask(task)
                    }
                    iterator.remove()
                    break
                }
            }
        }
    }
}