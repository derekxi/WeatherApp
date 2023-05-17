package com.example.weatherapp.data.repository

import com.example.weatherapp.domain.util.Resource
import com.example.weatherapp.data.mappers.toWeatherInfo
import com.example.weatherapp.data.remote.api.WeatherApi
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.weather.WeatherInfo
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
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
        } catch (e: HttpException){
            Resource.Error(e.message ?: "Oops, something went wrong")
        } catch (e: IOException){
            Resource.Error(e.message ?: "Couldn't reach server check your internet connection")
        } catch (e: Exception){
            Resource.Error(e.message ?: "An unknown error is occurred.")
        }
    }
}