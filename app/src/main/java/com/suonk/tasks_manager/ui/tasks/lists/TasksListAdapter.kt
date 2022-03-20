package com.suonk.tasks_manager.ui.tasks.lists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.suonk.tasks_manager.databinding.ItemTaskBinding

class TasksListAdapter(private val onClickedCallback: (Int, Int) -> Unit) :
    ListAdapter<TasksViewState, TasksListAdapter.ViewHolder>(TaskComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position), onClickedCallback)
    }

    inner class ViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(task: TasksViewState, onClicked: (Int, Int) -> Unit) {
            binding.apply {
                taskName.text = task.taskName
                projectName.text = task.projectName

                root.setOnClickListener {
                    onClicked(it.id, task.id.toInt())
                }

                imgDelete.setOnClickListener {
                    onClicked(it.id, task.id.toInt())
                }
            }
        }
    }

    class TaskComparator : DiffUtil.ItemCallback<TasksViewState>() {
        override fun areItemsTheSame(oldItem: TasksViewState, newItem: TasksViewState): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TasksViewState, newItem: TasksViewState): Boolean {
            return oldItem.projectName == newItem.projectName &&
                    oldItem.taskName == newItem.taskName
        }

    }
}