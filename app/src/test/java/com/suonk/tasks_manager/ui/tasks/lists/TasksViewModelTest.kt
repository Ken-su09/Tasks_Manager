package com.suonk.tasks_manager.ui.tasks.lists

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.suonk.tasks_manager.model.data.Task
import com.suonk.tasks_manager.repositories.tasks.TaskRepositoryImpl
import com.suonk.tasks_manager.use_case.tasks.TaskUseCases
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TasksViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private val taskRepositoryImpl: TaskRepositoryImpl = TODO()

    private lateinit var taskUseCases: TaskUseCases

    private val meetingsMutableLiveData = MutableLiveData<List<Task>>()

    private var viewModel: TasksViewModel? = null

    @Before
    fun setUp() {
        meetingsMutableLiveData

        given(taskRepositoryImpl.getAllTasks().asLiveData()).willReturn(meetingsMutableLiveData)

//        viewModel = TasksViewModel(taskUseCases)
    }

    @After
    fun tearDown() {
    }
}