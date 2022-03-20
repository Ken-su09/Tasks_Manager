package com.suonk.tasks_manager.ui

import android.annotation.SuppressLint
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.Navigation
import com.suonk.tasks_manager.R
import com.suonk.tasks_manager.databinding.FragmentSplashScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : BaseFragment() {

    private var binding: FragmentSplashScreenBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appLogoAnimation()
    }

    private fun appLogoAnimation() {
        val animation = AnimationUtils.loadAnimation(context, R.anim.suddenly_appear)
        val frameAnimation = binding?.appLogo?.drawable as AnimationDrawable
        frameAnimation.start()
        binding?.appName?.startAnimation(animation)

        CoroutineScope(Dispatchers.Main).launch {
            delay(2500)
            frameAnimation.stop()
            Navigation.findNavController(binding?.root as View).navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToTasksListFragment())
        }
    }

    //region ========================================== Lifecycle ===========================================

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    //endregion
}