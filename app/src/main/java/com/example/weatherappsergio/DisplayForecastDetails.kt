package com.example.weatherappsergio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherappsergio.MainActivity.Companion.CITY
import com.example.weatherappsergio.data.OpenWeatherMapApiService
import com.example.weatherappsergio.databinding.ActivityDisplayForecastDetailsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DisplayForecastDetails : AppCompatActivity() {

    private lateinit var binding: ActivityDisplayForecastDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayForecastDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GlobalScope.launch(Dispatchers.Main) {
            val city = intent.getStringExtra(CITY).toString()
            //Get the data from the weather API
            val apiService = OpenWeatherMapApiService()
            val currentWeatherResponse = apiService.getCurrentWeather(city).await()
            val degreesScale = getString(R.string.fahrenheit_degrees)
            val feelsLike = getString(R.string.feels_like)
            binding.temperatureDetail.text =
                currentWeatherResponse.list[0].main.temp.toString() + degreesScale
            binding.feelsLike.text =
                feelsLike + currentWeatherResponse.list[0].main.feels_like.toString() + degreesScale
            binding.weatherMain.text = currentWeatherResponse.list[0].weather[0].main
            binding.weatherDescription.text = currentWeatherResponse.list[0].weather[0].description
        }
    }
}