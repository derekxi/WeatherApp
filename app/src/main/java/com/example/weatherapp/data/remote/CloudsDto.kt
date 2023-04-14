package com.example.weatherapp.data.remote


import com.squareup.moshi.Json

data class CloudsDto(
    @field:Json(name = "all")
    val all: Int
)