package com.example.weatherappsergio

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherappsergio.MainActivity.Companion.CITY
import com.example.weatherappsergio.data.OpenWeatherMapApiService
import com.example.weatherappsergio.databinding.ActivityDisplayForecastBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DisplayForecast : AppCompatActivity() {

    private lateinit var binding: ActivityDisplayForecastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayForecastBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.showDetailsButton.setOnClickListener { displayForecastDetails() }

        GlobalScope.launch(Dispatchers.Main) {
            val city = intent.getStringExtra(CITY).toString()
            //Get the data from the weather API
            val apiService = OpenWeatherMapApiService()
            val currentWeatherResponse = apiService.getCurrentWeather(city).await()
            val temperaturesData = currentWeatherResponse.list[0].main
            val degreesScale = getString(R.string.fahrenheit_degrees)
            binding.temperatureDegrees.text = temperaturesData.temp.toString() + degreesScale
            binding.temperatureDegreesMax.text = temperaturesData.temp_max.toString() + degreesScale
            binding.temperatureDegreesMin.text = temperaturesData.temp_min.toString() + degreesScale
        }
    }

    private fun displayForecastDetails() {
        val city = intent.getStringExtra(CITY).toString()
        val intent = Intent(this, DisplayForecastDetails::class.java).apply {
            putExtra(CITY, city)
        }
        startActivity(intent)
    }
}