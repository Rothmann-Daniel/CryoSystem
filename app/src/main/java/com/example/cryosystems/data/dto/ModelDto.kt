package com.example.cryosystems.data.dto

import com.example.cryosystems.domain.model.Model

data class ModelDto(
    val id: Int,
    val name: String,
    val type: String,
    val time: String,
    val level: String,
    val currentTemp: String,
    val lowTemp: String,
    val highTemp: String,
    val loLevel: String,
    val hiLevel: String,
    val levelAlarm: String,
    val tempAlarm: String,
    val accumulator: String
)

fun ModelDto.toModel() = Model(
    id = id,
    name = name,
    type = type,
    time = time,
    level = level,
    currentTemp = currentTemp,
    lowTemp = lowTemp,
    highTemp = highTemp,
    loLevel = loLevel,
    hiLevel = hiLevel,
    levelAlarm = levelAlarm,
    tempAlarm = tempAlarm,
    accumulator = accumulator
)
