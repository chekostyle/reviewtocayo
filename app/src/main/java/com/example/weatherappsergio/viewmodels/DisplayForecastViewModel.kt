package com.example.weatherappsergio.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappsergio.constants.FAHRENHEIT_DEGREES_SYMBOL
import com.example.weatherappsergio.network.OpenWeatherMapApiService
import kotlinx.coroutines.launch

class DisplayForecastViewModel: ViewModel() {

    //Todo get the input citi from MainActivity
    val city = "Atlanta"

    private val _temperatureDegrees = MutableLiveData<String>()
    private val _temperatureDegreesMax = MutableLiveData<String>()
    private val _temperatureDegreesMin = MutableLiveData<String>()

    val temperatureDegrees: LiveData<String> = _temperatureDegrees
    val temperatureDegreesMax: LiveData<String> = _temperatureDegreesMax
    val temperatureDegreesMin: LiveData<String> = _temperatureDegreesMin

    init {
        getForecastForSelectedCity()
    }

    private fun getForecastForSelectedCity() {
        viewModelScope.launch {
            val responseMain = OpenWeatherMapApiService().getCurrentWeatherAsync(
                city).await().list[0].main

            _temperatureDegrees.value = responseMain.temp.toString() + FAHRENHEIT_DEGREES_SYMBOL
            _temperatureDegreesMax.value = responseMain.temp_max.toString() + FAHRENHEIT_DEGREES_SYMBOL
            _temperatureDegreesMin.value = responseMain.temp_min.toString() + FAHRENHEIT_DEGREES_SYMBOL
        }
    }
}