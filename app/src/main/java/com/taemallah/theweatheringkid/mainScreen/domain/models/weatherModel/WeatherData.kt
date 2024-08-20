package com.taemallah.theweatheringkid.mainScreen.domain.models.weatherModel

data class WeatherData (
    val location: Location,
    val condition: Condition,
    val cloud: Int,
    val feelslike_c: Double,
    val humidity: Int,
    val last_updated: String,
    val last_updated_epoch: Int,
    val pressure_mb: Double,
    val temp_c: Double,
    val wind_dir: String,
    val wind_kph: Double,
)