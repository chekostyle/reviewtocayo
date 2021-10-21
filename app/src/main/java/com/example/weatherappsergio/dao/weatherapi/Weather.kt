package com.example.weatherappsergio.dao.weatherapi

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)