package com.example.sensordemo.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensordemo.data.model.SensorData
import com.example.sensordemo.data.repository.SensorRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: SensorRepository) : ViewModel() {
    val sensorData: StateFlow<SensorData> = repository.sensorData

    fun updateSensorData(x: Float, y: Float, z: Float) {
        viewModelScope.launch {
            repository.updateSensorData(x, y, z)
        }
    }
}