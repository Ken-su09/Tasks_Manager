package com.suonk.tasks_manager.ui.tasks.lists

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatSpinner
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.suonk.tasks_manager.R
import com.suonk.tasks_manager.databinding.FragmentTasksListBinding
import com.suonk.tasks_manager.ui.BaseFragment
import com.suonk.tasks_manager.ui.tasks.create.CreateTaskDialogFragment
import com.suonk.tasks_manager.ui.tasks.create.CreateTaskViewState
import com.suonk.tasks_manager.utils.OrderType
import com.suonk.tasks_manager.utils.TaskOrder
import com.suonk.tasks_manager.utils.TasksEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalTime

@AndroidEntryPoint
class TasksListFragment : BaseFragment() {

    private var binding: FragmentTasksListBinding? = null
    private val viewModel: TasksViewModel by activityViewModels()
    private lateinit var sharedPreferences: SharedPreferences

    //region ========================================== Lifecycle ===========================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        sharedPreferences = cxt.getSharedPreferences("sortBy", Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTasksListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupActionBar()
        initRecyclerView()
        addNewTaskFabClick()
    }

    private fun addNewTaskFabClick() {
        binding?.addTask?.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                binding?.addTask?.isEnabled = false
                delay(100)
                binding?.addTask?.isEnabled = true
            }
            viewModel.removeSourceFilterNameLiveData()
            navController.navigate(
                TasksListFragmentDirections.actionTasksListFragmentToCreateTaskDialogFragment()
            )
        }
    }

    private fun initRecyclerView() {
        val tasksListAdapter = TasksListAdapter { view, id ->
            when (view) {
                R.id.item_root -> {
//                    viewModel.removeSourceFilterNameLiveData()
//                    navController.navigate(
//                        TasksListFragmentDirections.actionTasksListFragmentToTaskDetailsFragment(
//                            id.toLong()
//                        )
//                    )
                }
                R.id.img_delete -> {
                    viewModel.removeSourceFilterNameLiveData()
                    viewModel.onEvent(TasksEvent.DeleteTask(id.toLong()))
                }
            }
        }

        binding?.tasksRv?.apply {
            adapter = tasksListAdapter
            viewModel.getAllTasksViewState().observe(viewLifecycleOwner) { tasks ->
                binding?.noTaskText?.isVisible = tasks.isEmpty()
                binding?.noTaskImage?.isVisible = tasks.isEmpty()

                tasksListAdapter.submitList(tasks)
            }
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(cxt)
        }
    }

    //region =========================================== Action Bar =========================================

    private fun setupActionBar() {
        binding?.toolbar?.apply {
            (activity as AppCompatActivity).setSupportActionBar(this)

            setBackgroundColor(ContextCompat.getColor(cxt, R.color.bluePrimary))
            setTitleTextColor(AppCompatResources.getColorStateList(cxt, R.color.white))

            if ((activity as AppCompatActivity).supportActionBar != null) {
                (activity as AppCompatActivity).supportActionBar?.title = "Tasks Manager"
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_tasks_toolbar, menu)

        menu.getItem(sharedPreferences.getInt("sortBy", 0)).isChecked = true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId != R.id.sort) {
            item.isChecked = true
            viewModel.setFilterIdLiveData(item.itemId)
        }
//                viewModel.onEvent(TasksEvent.Order(TaskOrder.Date(OrderType.Descending).copy(OrderType.Descending)))
        return super.onOptionsItemSelected(item)
    }

    //endregion
}