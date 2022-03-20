package com.suonk.tasks_manager.ui.tasks.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suonk.tasks_manager.databinding.FragmentTaskDetailsBinding
import com.suonk.tasks_manager.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskDetailsFragment : BaseFragment() {

    private var binding: FragmentTaskDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    //region ========================================== Lifecycle ===========================================

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    //endregion
}