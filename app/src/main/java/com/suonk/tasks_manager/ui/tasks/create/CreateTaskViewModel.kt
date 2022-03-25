package com.suonk.tasks_manager.ui.tasks.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suonk.tasks_manager.use_case.tasks.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases
) : ViewModel() {

    fun insertNewTask(createTaskViewState: CreateTaskViewState) = viewModelScope.launch {
        taskUseCases.insertTask.invoke(createTaskViewState)
    }
}