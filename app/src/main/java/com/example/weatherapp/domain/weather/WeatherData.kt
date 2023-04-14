package com.example.weatherapp.domain.weather

import java.time.LocalDateTime

data class WeatherData(
    val time: LocalDateTime,
    val description: String,
    val city: String,
    val temperature: Double,
    val humidity: Int,
    val windSpeed: Double,
    val windDirection: Int,
    val weatherType: WeatherType
)
