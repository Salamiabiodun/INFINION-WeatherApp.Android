package com.example.weatherapp.data.model

data class WeatherResponse(
    val weather: List<Weather>,
    val main: Main,
    val name: String
)

data class Weather(
    val description: String
)

data class Main(
    val temp: Double
)
