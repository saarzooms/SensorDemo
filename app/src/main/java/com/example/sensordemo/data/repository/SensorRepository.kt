package com.example.sensordemo.data.repository

import com.example.sensordemo.data.model.SensorData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SensorRepository {
    private val _sensorData = MutableStateFlow(SensorData(0f, 0f, 0f))
    val sensorData: StateFlow<SensorData> = _sensorData

    fun updateSensorData(x: Float, y: Float, z: Float) {
        _sensorData.value = SensorData(x, y, z)
    }
}