package com.example.cryosystems.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.cryosystems.R
import com.example.cryosystems.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var tabMediator: TabLayoutMediator
    private lateinit var adapter: SystemItemAdapter

    private val mainViewModel: MainFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        observeTabSelection()
    }


    // Инициализация ViewPager и TabLayout
    private fun setupViewPager() {
        binding.viewPager.adapter = SystemViewPagerAdapter(childFragmentManager, lifecycle)

        // Восстанавливаем сохраненную позицию
        binding.viewPager.setCurrentItem(mainViewModel.selectedTab.value, false)

        // Обновляем ViewModel при смене таба
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                mainViewModel.selectTab(position)
            }
        })
        // Инициализируем TabLayout
        tabMediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.system)
                1 -> getString(R.string.chartlog)
                else -> ""
            }
        }
        tabMediator.attach()
    }
    // Обновляем ViewModel при смене таба
    private fun observeTabSelection() {
        // Дополнительная подписка на изменения (если нужно синхронизировать с другими компонентами)
        mainViewModel.selectedTab
            .onEach { position ->
                if (binding.viewPager.currentItem != position) {
                    binding.viewPager.setCurrentItem(position, false)
                }
            }
            .launchIn(lifecycleScope)
    }

    // Очистка ресурсов
    override fun onDestroy() {
        super.onDestroy()
        binding.viewPager.adapter = null
        tabMediator.detach()
        _binding = null
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}