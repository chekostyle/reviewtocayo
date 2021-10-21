package com.example.weatherappsergio.dao.weatherapi

data class CurrentWeatherResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ForecastItem>,
    val message: Int
)