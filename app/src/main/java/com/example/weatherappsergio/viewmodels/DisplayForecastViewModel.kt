package com.example.weatherappsergio.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappsergio.constants.FAHRENHEIT_DEGREES_SYMBOL
import com.example.weatherappsergio.dao.weatherapi.Main
import com.example.weatherappsergio.services.interfaces.IWeatherService
import kotlinx.coroutines.launch

class DisplayForecastViewModel(private val weatherService : IWeatherService): ViewModel() {

    private val _temperatureDegrees = MutableLiveData<String>()
    private val _temperatureDegreesMax = MutableLiveData<String>()
    private val _temperatureDegreesMin = MutableLiveData<String>()
    private val _city = MutableLiveData<String>()

    val temperatureDegrees: LiveData<String> = _temperatureDegrees
    val temperatureDegreesMax: LiveData<String> = _temperatureDegreesMax
    val temperatureDegreesMin: LiveData<String> = _temperatureDegreesMin
    val city: LiveData<String> = _city

    var main : Main? = null

    fun getForecastForSelectedCity() {
        viewModelScope.launch {

            val result = weatherService.getWeather(_city.value ?: "")
            val forecast = result?.list?.firstOrNull()

            forecast?.let {
                _temperatureDegrees.value = it.main.temp.toString() + FAHRENHEIT_DEGREES_SYMBOL
                _temperatureDegreesMax.value = it.main.temp_max.toString() + FAHRENHEIT_DEGREES_SYMBOL
                _temperatureDegreesMin.value = it.main.temp_min.toString() + FAHRENHEIT_DEGREES_SYMBOL
            }
        }
    }

    fun setCity(city: String) {
        _city.value = city
    }
}