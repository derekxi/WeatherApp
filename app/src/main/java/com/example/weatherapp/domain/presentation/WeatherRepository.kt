package com.example.weatherapp.domain.presentation

import com.example.weatherapp.domain.util.Resource
import com.example.weatherapp.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, longi: Double): Resource<WeatherInfo>
}