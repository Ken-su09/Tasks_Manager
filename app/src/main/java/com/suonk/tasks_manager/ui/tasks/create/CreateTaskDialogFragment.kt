package com.suonk.tasks_manager.ui.tasks.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatSpinner
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.suonk.tasks_manager.R
import com.suonk.tasks_manager.databinding.CreateTaskDialogFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateTaskDialogFragment : DialogFragment() {

    private val viewModel: CreateTaskViewModel by activityViewModels()
    private var spinnerName = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_task_dialog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.apply {
            setupSpinner(findViewById(R.id.add_task_spinner))
            findViewById<AppCompatButton>(R.id.add_task_button).setOnClickListener {
                if (view.findViewById<AppCompatEditText>(R.id.add_task_edit_text).text.toString()
                        .isEmpty()
                ) {
                    Toast.makeText(context, "Task name should not be empty", Toast.LENGTH_LONG)
                        .show()
                } else {
                    viewModel.insertNewTask(
                        CreateTaskViewState(
                            view.findViewById<AppCompatEditText>(R.id.add_task_edit_text).text.toString(),
                            spinnerName
                        )
                    )
                    dialog?.dismiss()
                    dialog?.cancel()
                }
            }
            findViewById<AppCompatButton>(R.id.cancel_dialog_button).setOnClickListener {
                dialog?.dismiss()
                dialog?.cancel()
            }
        }
    }

    //region ============================================ Spinner ===========================================

    private fun setupSpinner(spinner: AppCompatSpinner) {
        val adapter = context?.let {
            ArrayAdapter.createFromResource(
                it, R.array.projects_array,
                android.R.layout.simple_spinner_item
            )
        }
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                spinnerName = spinner.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

    //endregion

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }
}