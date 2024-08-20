package com.taemallah.theweatheringkid.mainScreen.presentation

import com.taemallah.theweatheringkid.mainScreen.domain.models.weatherModel.WeatherData

data class WeatherState(
    var data: WeatherData? = null,
    var errorMessage: String = "",
    var isSuccessfulRequest: Boolean = false,
    var city: String = "",
    var isVisibleCitySelector: Boolean = false
)
