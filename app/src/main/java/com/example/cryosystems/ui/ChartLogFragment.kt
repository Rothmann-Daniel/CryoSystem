package com.example.cryosystems.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cryosystems.R
import com.example.cryosystems.databinding.FragmentChartLogBinding

class ChartLogFragment : Fragment() {

    private var _binding: FragmentChartLogBinding? = null
    private val binding get() = _binding!!



    private val viewModel: ChartLogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentChartLogBinding.inflate(inflater, container, false)
        return binding.root
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = ChartLogFragment()
    }


}