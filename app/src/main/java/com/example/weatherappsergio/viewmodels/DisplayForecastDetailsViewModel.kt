package com.example.weatherappsergio.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappsergio.constants.FAHRENHEIT_DEGREES_SYMBOL
import com.example.weatherappsergio.services.interfaces.IWeatherService
import kotlinx.coroutines.launch

class DisplayForecastDetailsViewModel(private val weatherService : IWeatherService): ViewModel() {

    private val _temperatureDegrees = MutableLiveData<String>()
    private val _temperatureFeelsLike = MutableLiveData<String>()
    private val _weatherMain = MutableLiveData<String>()
    private val _weatherDescription = MutableLiveData<String>()
    private val _city = MutableLiveData<String>()

    val temperatureDegrees: LiveData<String> = _temperatureDegrees
    val temperatureFeelsLike: LiveData<String> = _temperatureFeelsLike
    val weatherMain: LiveData<String> = _weatherMain
    val weatherDescription: LiveData<String> = _weatherDescription
    val city: LiveData<String> = _city

    fun getForecastForSelectedCity() {
        viewModelScope.launch {
            val result = weatherService.getWeather(_city.value ?: "")
            val forecast = result?.list?.firstOrNull()

            forecast?.let {
                _temperatureDegrees.value = it.main.temp.toString() + FAHRENHEIT_DEGREES_SYMBOL
                _temperatureFeelsLike.value = "Feels Like: " + it.main.feels_like.toString() + FAHRENHEIT_DEGREES_SYMBOL
                val weather = it.weather.firstOrNull()
                weather?.let { w ->
                    _weatherMain.value = w.main.uppercase()
                    _weatherDescription.value = w.description.uppercase()
                }

            }
        }
    }

    fun setCity(city: String) {
        _city.value = city
    }
}