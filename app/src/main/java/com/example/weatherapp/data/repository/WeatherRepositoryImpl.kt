package com.example.weatherapp.data.repository

import com.example.weatherapp.domain.util.Resource
import com.example.weatherapp.data.mappers.toWeatherInfo
import com.example.weatherapp.data.remote.api.WeatherApi
import com.example.weatherapp.domain.presentation.WeatherRepository
import com.example.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): WeatherRepository {
    override suspend fun getWeatherData(lat: Double, longi: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getCurrentWeatherData(
                    latitude = lat,
                    longitude = longi
                ).toWeatherInfo()
            )
        } catch (e: Exception){
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error is occurred.")
        }
    }
}