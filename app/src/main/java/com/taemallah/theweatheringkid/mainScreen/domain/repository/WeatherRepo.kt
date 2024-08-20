package com.taemallah.theweatheringkid.mainScreen.domain.repository

import com.taemallah.theweatheringkid.mainScreen.domain.models.weatherModel.WeatherResponse

interface WeatherRepo {
    suspend fun getWeatherData(city: String):WeatherResponse
}