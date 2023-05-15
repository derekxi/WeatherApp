package com.example.weatherapp.presentation.navigation


sealed class Screens(val route: String){
    object WeatherScreen:Screens("Weather_Screen")
    object SearchScreen:Screens("Search_Screen")
}
