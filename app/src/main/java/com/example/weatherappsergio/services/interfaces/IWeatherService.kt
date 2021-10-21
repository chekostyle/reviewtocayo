package com.example.weatherappsergio.services.interfaces

import com.example.weatherappsergio.dao.weatherapi.CurrentWeatherResponse

interface IWeatherService {
    suspend fun getWeather(city: String): CurrentWeatherResponse?
}