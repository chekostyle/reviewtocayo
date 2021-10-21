package com.example.weatherappsergio.viewmodels

import android.provider.Settings.Global.getString
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappsergio.R
import com.example.weatherappsergio.constants.FAHRENHEIT_DEGREES_SYMBOL
import com.example.weatherappsergio.network.OpenWeatherMapApiService
import kotlinx.coroutines.launch

class DisplayForecastDetailsViewModel: ViewModel() {

    //Todo get the input citi from MainActivity
    val city = "Atlanta"

    private val _temperatureDegrees = MutableLiveData<String>()
    private val _temperatureFeelsLike = MutableLiveData<String>()
    private val _weatherMain = MutableLiveData<String>()
    private val _weatherDescription = MutableLiveData<String>()

    val temperatureDegrees: LiveData<String> = _temperatureDegrees
    val temperatureFeelsLike: LiveData<String> = _temperatureFeelsLike
    val weatherMain: LiveData<String> = _weatherMain
    val weatherDescription: LiveData<String> = _weatherDescription

    init {
        getForecastForSelectedCity()
    }

    private fun getForecastForSelectedCity() {
        viewModelScope.launch {
            val responseDetails = OpenWeatherMapApiService().getCurrentWeatherAsync(
                city).await().list[0]
            _temperatureDegrees.value = responseDetails.main.temp.toString() + FAHRENHEIT_DEGREES_SYMBOL
            _temperatureFeelsLike.value = "Feels Like: " + responseDetails.main.feels_like.toString() + FAHRENHEIT_DEGREES_SYMBOL
            _weatherMain.value = responseDetails.weather[0].main.uppercase()
            _weatherDescription.value = responseDetails.weather[0].description.uppercase()
        }
    }
}