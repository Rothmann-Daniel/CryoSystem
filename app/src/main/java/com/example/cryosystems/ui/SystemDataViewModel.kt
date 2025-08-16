package com.example.cryosystems.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryosystems.domain.model.Model

class SystemDataViewModel : ViewModel() {
    private val _models = MutableLiveData<List<Model>>()
    val models: LiveData<List<Model>> = _models

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadData() {
        _isLoading.value = true

        // Здесь должна быть ваша логика загрузки данных
        // Пока используем тестовые данные как в вашем фрагменте
        val sampleData = listOf(
            Model(
                id = 1,
                name = "CryoSystem-1",
                type = "Type-A",
                time = "Sunday 16/08/2025 10:00",
                level = "90",
                currentTemp = "-195",
                lowTemp = "-200",
                highTemp = "-180",
                loLevel = "10",
                hiLevel = "100",
                levelAlarm = "0",
                tempAlarm = "0",
                accumulator = "85"
            )
        )

        _models.value = sampleData
        _isLoading.value = false
    }
}