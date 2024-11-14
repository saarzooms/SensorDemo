package com.example.sensordemo

import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.sensordemo.R
import com.example.sensordemo.data.repository.SensorRepository
import com.example.sensordemo.databinding.ActivityMainBinding
import com.example.sensordemo.ui.SensorEventListenerImpl
import com.example.sensordemo.ui.main.MainViewModel
import com.example.sensordemo.ui.main.MainViewModelFactory
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private lateinit var sensorEventListener: SensorEventListenerImpl

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(SensorRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorEventListener = SensorEventListenerImpl(viewModel)

        // Observe sensor data
        lifecycleScope.launchWhenStarted {
            viewModel.sensorData.collect { data ->
                binding.xValue.text = "X: ${data.x}"
                binding.yValue.text = "Y: ${data.y}"
                binding.zValue.text = "Z: ${data.z}"
            }
        }
    }

    override fun onResume() {
        super.onResume()
        accelerometer?.let {
            sensorManager.registerListener(sensorEventListener, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(sensorEventListener)
    }
}