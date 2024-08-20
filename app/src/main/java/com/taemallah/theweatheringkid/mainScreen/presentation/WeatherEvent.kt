package com.taemallah.theweatheringkid.mainScreen.presentation

sealed class WeatherEvent {
    data class SaveCityToPreferences (val city: String): WeatherEvent()
    object LoadCityFromPreferences : WeatherEvent()
    object LoadWeatherData : WeatherEvent()
    object HideCitySelector : WeatherEvent()
    object ShowCitySelector : WeatherEvent()
}