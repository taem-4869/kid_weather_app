package com.taemallah.theweatheringkid.mainScreen.domain.models.weatherModel

sealed class WeatherResponse(open val message: String="", open val data: WeatherData?=null){
    data class Success(override val data: WeatherData): WeatherResponse()
    data class Failure(override val message: String): WeatherResponse()
}
