package com.example.cryosystems.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class SystemViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle){
    override fun getItemCount(): Int = 2 // Количество вкладок

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SystemDataFragment.newInstance()
            1 -> ChartLogFragment.newInstance()
            else -> SystemDataFragment.newInstance()
        }
    }
}