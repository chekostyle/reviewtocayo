package com.example.weatherappsergio.services.implementations

import com.example.weatherappsergio.dao.weatherapi.CurrentWeatherResponse
import com.example.weatherappsergio.services.interfaces.IWeatherService
import com.example.weatherappsergio.services.interfaces.retrofit.IOpenWeatherMapApiService
import java.lang.Exception

class WeatherServiceImpl(private val weatherService : IOpenWeatherMapApiService) : IWeatherService {

    override suspend fun getWeather(city: String): CurrentWeatherResponse? {
        return try {
            weatherService.getCurrentWeatherAsync(city).let {
                if (it.isSuccessful) {
                    it.body()
                } else {
                    null
                }
            }
        } catch (e: Exception){
            null
        }
    }

}