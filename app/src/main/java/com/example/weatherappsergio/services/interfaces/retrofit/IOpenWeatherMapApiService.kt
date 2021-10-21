package com.example.weatherappsergio.services.interfaces.retrofit

import com.example.weatherappsergio.dao.weatherapi.CurrentWeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IOpenWeatherMapApiService {
    @GET("forecast")
    suspend fun getCurrentWeatherAsync(@Query("q") location: String): Response<CurrentWeatherResponse>
}