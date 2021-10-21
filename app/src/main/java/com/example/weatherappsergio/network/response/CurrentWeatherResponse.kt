package com.example.weatherappsergio.network.response

data class CurrentWeatherResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ForecastItem>,
    val message: Int
)