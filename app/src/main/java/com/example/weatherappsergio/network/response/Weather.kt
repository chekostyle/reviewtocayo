package com.example.weatherappsergio.network.response

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)