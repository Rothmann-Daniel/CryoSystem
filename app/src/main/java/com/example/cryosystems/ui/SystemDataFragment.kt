package com.example.cryosystems.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryosystems.R
import com.example.cryosystems.databinding.FragmentSystemDataBinding
import com.example.cryosystems.domain.model.Model
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SystemDataFragment : Fragment() {

    private var _binding: FragmentSystemDataBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: SystemItemAdapter
    private val viewModel: SystemDataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSystemDataBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
        loadData()
    }

    private fun setupRecyclerView() {
        adapter = SystemItemAdapter(emptyList()) { model ->
            // Обработка клика по элементу
            showDetailsDialog(model)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@SystemDataFragment.adapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
        }
    }


    private fun showDetailsDialog(model: Model) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Детали системы")
            .setMessage(
                """
                Имя: ${model.name}
                Тип: ${model.type}
                Температура: ${model.currentTemp}℃
                Уровень: ${model.level}
                Авария температуры: ${if (model.tempAlarm == "1") "Да" else "Нет"}
                Авария уровня: ${if (model.levelAlarm == "1") "Да" else "Нет"}
                """.trimIndent()
            )
            .setPositiveButton("OK", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun loadData() {
        viewModel.loadData()
    }

    private fun setupObservers() {
        viewModel.models.observe(viewLifecycleOwner) { models ->
            adapter.updateData(models)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    companion object {
        fun newInstance() = SystemDataFragment()
    }

}