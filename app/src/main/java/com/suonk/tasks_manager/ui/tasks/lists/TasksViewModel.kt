package com.suonk.tasks_manager.ui.tasks.lists

import androidx.lifecycle.*
import com.suonk.tasks_manager.R
import com.suonk.tasks_manager.model.data.Project
import com.suonk.tasks_manager.model.data.Task
import com.suonk.tasks_manager.use_case.projects.ProjectUseCases
import com.suonk.tasks_manager.use_case.tasks.TaskUseCases
import com.suonk.tasks_manager.utils.TasksEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    private val projectUseCases: ProjectUseCases,
) : ViewModel() {

    private val getAllTasks = MediatorLiveData<List<TasksViewState>>()
    private val filterIdLiveData = MutableLiveData(R.id.sort_by_name)

    private fun combine() {
        getAllTasks.addSource(taskUseCases.getAllTasks.invoke().asLiveData()) { tasks ->
            viewModelScope.launch {
                projectUseCases.getAllProjects.invoke().collect { projects ->
                    getAllTasks.addSource(filterIdLiveData) { filterId ->
                        combine(
                            tasks,
                            projects,
                            filterId
                        )
                    }
                }
            }
        }
    }

    private fun combine(tasks: List<Task>?, projects: List<Project>?, filterId: Int) {
        val listOfTasksViewState = arrayListOf<TasksViewState>()
        if (tasks != null) {
            for (task in tasks) {
                if (projects != null) {
                    for (project in projects) {
                        if (project.id == task.projectId) {
                            listOfTasksViewState.add(
                                TasksViewState(
                                    task.id,
                                    task.name,
                                    project.name,
                                    project.color
                                )
                            )
                        }
                    }
                }
            }
        }

        when (filterId) {
            R.id.sort_by_date -> {
                getAllTasks.value = listOfTasksViewState.sortedByDescending { it.id }
            }
            R.id.sort_by_name -> {
                getAllTasks.value = listOfTasksViewState.sortedBy { it.taskName }
            }
            R.id.sort_by_project -> {
                getAllTasks.value = listOfTasksViewState.sortedBy { it.projectName }
            }
            else -> {
                getAllTasks.value = listOfTasksViewState.sortedBy { it.taskName }
            }
        }
    }

    fun getAllTasksViewState(): MutableLiveData<List<TasksViewState>> {
        combine()
        return getAllTasks
    }

    fun removeSourceFilterNameLiveData() {
        getAllTasks.removeSource(filterIdLiveData)
    }

    fun setFilterIdLiveData(filterId: Int) {
        filterIdLiveData.value = filterId
    }

    fun onEvent(event: TasksEvent) {
        when (event) {
            is TasksEvent.Order -> {
                viewModelScope.launch {
                    taskUseCases.getAllTasks.invoke(event.taskOrder)
                }
            }
            is TasksEvent.DeleteTask -> {
                viewModelScope.launch {
                    taskUseCases.deleteTask.invoke(event.id)
                }
            }
        }
    }
}