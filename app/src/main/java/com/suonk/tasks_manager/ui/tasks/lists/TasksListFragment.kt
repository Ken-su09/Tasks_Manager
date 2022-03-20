package com.suonk.tasks_manager.ui.tasks.lists

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.suonk.tasks_manager.R
import com.suonk.tasks_manager.databinding.FragmentTasksListBinding
import com.suonk.tasks_manager.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class TasksListFragment : BaseFragment() {

    private var binding: FragmentTasksListBinding? = null
    private val viewModel: TasksViewModel by activityViewModels()

    private lateinit var tasksListAdapter: TasksListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTasksListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupActionBar()
        initRecyclerView()
        addNewTaskFabClick()
    }

    private fun setupActionBar() {
        binding?.toolbar?.let {
            cxt.setSupportActionBar(it)
            it.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.bluePrimary
                )
            )
            it.setTitleTextColor(
                AppCompatResources.getColorStateList(
                    requireActivity(),
                    R.color.white
                )
            )
        }
        if (cxt.supportActionBar != null) {
            cxt.supportActionBar?.title = "Todoc"
        }
    }

    private fun initRecyclerView() {
        binding?.tasksRv?.apply {
            tasksListAdapter = TasksListAdapter { view, id ->
//                when(view){
//                    is R.id.
//                }
                Navigation.findNavController(binding?.root as View).navigate(
                    TasksListFragmentDirections.actionTasksListFragmentToTaskDetailsFragment(id.toLong())
                )
            }
            adapter = tasksListAdapter
//            viewModel.getAllTasks.observe(viewLifecycleOwner, { tasks ->
//                tasksListAdapter.submitList(tasks)
//        })
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(cxt)
        }
    }

    private fun addNewTaskFabClick() {
        binding?.addTask?.setOnClickListener {
            Log.i("testId", "1 : ${it.id}")
            Log.i("testId", "2 : ${it}")
//            MaterialAlertDialogBuilder(cxt)
//                .setTitle(resources.getString(R.string.add_task_dialog_title))
//                .setNegativeButton(resources.getString(R.string.add_task_dialog_positive_button)) { dialog, which ->
//                }
//                .setPositiveButton(resources.getString(R.string.add_task_dialog_negative_button)) { dialog, which ->
//                    viewModel.onEvent(TasksEvent.AddTask(TasksViewState()))
//                }
//                .show()
        }
    }

    //region ========================================== Lifecycle ===========================================

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    //endregion
}