package com.example.sensordemo.ui

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import com.example.sensordemo.ui.main.MainViewModel

class SensorEventListenerImpl(private val viewModel: MainViewModel) : SensorEventListener {
    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            viewModel.updateSensorData(it.values[0], it.values[1], it.values[2])
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Optional - no specific implementation needed for this demo
    }
}