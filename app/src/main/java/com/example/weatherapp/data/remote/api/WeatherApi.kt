package com.example.weatherapp.data.remote.api

import com.example.weatherapp.data.remote.CurrentWeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather")
    suspend fun getCurrentWeatherData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String = "8d4e9193c712acac2e4a85f15b14326a",
        @Query("units") units: String = "metric"
    ): CurrentWeatherDto
}