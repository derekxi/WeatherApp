package com.example.weatherapp.data.mappers

import com.example.weatherapp.data.remote.CurrentWeatherDto
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherInfo
import com.example.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.ZoneOffset

fun CurrentWeatherDto.toWeatherInfo(): WeatherInfo{
    val currentWeather = WeatherData(
        time = LocalDateTime.ofEpochSecond(dt.toLong(), 0, ZoneOffset.UTC),
        description = weatherDto[0].description,
        city = name,
        temperature = mainDto.temp,
        humidity = mainDto.humidity,
        windSpeed = windDto.speed,
        windDirection = windDto.deg,
        weatherType = WeatherType.fromWeatherCode(weatherDto[0].id)
    )
    return WeatherInfo(currentWeather)
}