package com.example.weatherapp.data.remote


import com.squareup.moshi.Json


data class RainDto(
    @field:Json(name = "1h")
    val h: Double
)