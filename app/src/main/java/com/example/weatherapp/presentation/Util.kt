package com.example.weatherapp.presentation

object Util {

    private val DIRECTIONS = listOf(
        "N",
        "NE",
        "E",
        "SE",
        "S",
        "SW",
        "W",
        "NW"
    )

    fun getWindDirection(windDirection: Int): String{
        return DIRECTIONS[(windDirection % 360 / 45 % 8)]
    }



}