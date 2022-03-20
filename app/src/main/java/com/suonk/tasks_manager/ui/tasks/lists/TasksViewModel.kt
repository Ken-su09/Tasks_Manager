package com.suonk.tasks_manager.ui.tasks.lists

import androidx.lifecycle.*
import com.suonk.tasks_manager.use_case.tasks.TaskUseCases
import com.suonk.tasks_manager.utils.TasksEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(private val taskUseCases: TaskUseCases) : ViewModel() {

    private val getAllTasks = taskUseCases.getAllTasks.invoke().asLiveData()

//    fun getAllTasksViewState() : LiveData<List<TasksViewState>>{
//        return getAllTasks.map {
//            val listOfTasksViewState = arrayListOf<TasksViewState>()
//            for(task in it){
//                task.apply {
//                    val tasksViewState = TasksViewState(id, name, p)
//                }
//
//                listOfTasksViewState.addAll(it)
//            }
//        }
//    }

    fun onEvent(event: TasksEvent) {
        when (event) {
            is TasksEvent.Order -> {

            }
            is TasksEvent.AddTask -> {
                viewModelScope.launch {
                    taskUseCases.insertTask(event.task)
                }
            }
            is TasksEvent.DeleteTask -> {
                viewModelScope.launch {
                    taskUseCases.deleteTask(event.task)
                }
            }
            is TasksEvent.ToggleOrderSection -> {
                viewModelScope.launch {
                }
            }
        }
    }
}