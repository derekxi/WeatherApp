package com.example.weatherapp.domain.weather

import androidx.annotation.DrawableRes
import com.example.weatherapp.R

sealed class WeatherType(
    val weatherDesc: String,
    @DrawableRes val backgroundRes: Int
){
    object Thunderstorm: WeatherType(
        weatherDesc = "Thunderstorm",
        backgroundRes = R.drawable.weather_app_thunder
    )

    object Drizzle: WeatherType(
        weatherDesc = "Drizzle",
        backgroundRes = R.drawable.weather_app_rain
    )

    object Rain: WeatherType(
        weatherDesc = "Rain",
        backgroundRes = R.drawable.weather_app_rain
    )

    object Snow: WeatherType(
        weatherDesc = "Snow",
        backgroundRes = R.drawable.weather_app_snow
    )

    object Clear: WeatherType(
        weatherDesc = "Clear",
        backgroundRes = R.drawable.weather_app_sunny
    )

    object Clouds: WeatherType(
        weatherDesc = "Cloudy",
        backgroundRes = R.drawable.weather_app_clouds
    )

    companion object{
        fun fromWeatherCode(code: Int): WeatherType{
            return when(code){
                in 200..232 -> Thunderstorm
                in 300..321 -> Drizzle
                in 500..531 -> Rain
                in 600..622 -> Snow
                800 -> Clear
                else -> Clear
            }
        }
    }
}
