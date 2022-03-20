package com.suonk.tasks_manager.ui

import androidx.fragment.app.Fragment
import com.suonk.tasks_manager.ui.MainActivity

abstract class BaseFragment : Fragment() {

    protected val cxt by lazy {
        activity as MainActivity
    }
}