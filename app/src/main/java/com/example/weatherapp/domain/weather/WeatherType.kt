package com.example.weatherapp.domain.weather

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.weatherapp.R
import com.example.weatherapp.presentation.ui.theme.WeatherBlue
import com.example.weatherapp.presentation.ui.theme.WeatherDarkBlue
import com.example.weatherapp.presentation.ui.theme.WeatherPink

sealed class WeatherType(
    val weatherDesc: String,
    val textcolor: Color,
    @DrawableRes val backgroundRes: Int
){
    object Thunderstorm: WeatherType(
        weatherDesc = "Thunderstorm",
        textcolor = WeatherBlue,
        backgroundRes = R.drawable.weather_app_thunder
    )

    object Drizzle: WeatherType(
        weatherDesc = "Drizzle",
        textcolor = WeatherDarkBlue,
        backgroundRes = R.drawable.weather_app_rain
    )

    object Rain: WeatherType(
        weatherDesc = "Rain",
        textcolor = WeatherDarkBlue,
        backgroundRes = R.drawable.weather_app_rain
    )

    object Snow: WeatherType(
        weatherDesc = "Snow",
        textcolor = WeatherDarkBlue,
        backgroundRes = R.drawable.weather_app_snow
    )

    object Clear: WeatherType(
        weatherDesc = "Clear",
        textcolor = WeatherPink,
        backgroundRes = R.drawable.weather_app_sunny
    )

    object Clouds: WeatherType(
        weatherDesc = "Cloudy",
        textcolor = WeatherBlue,
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
