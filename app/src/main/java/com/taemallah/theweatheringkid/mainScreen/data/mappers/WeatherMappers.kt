package com.taemallah.theweatheringkid.mainScreen.data.mappers

import com.taemallah.theweatheringkid.mainScreen.domain.models.weatherModel.WeatherData
import com.taemallah.theweatheringkid.mainScreen.domain.models.weatherModel.WeatherDataDto

fun WeatherDataDto.toWeatherData():WeatherData{
    return WeatherData(
        location = location,
        condition = current.condition.copy(icon = "https:"+current.condition.icon),
        cloud = current.cloud,
        feelslike_c = current.feelslike_c,
        humidity = current.humidity,
        last_updated = current.last_updated,
        last_updated_epoch = current.last_updated_epoch,
        pressure_mb = current.pressure_mb,
        temp_c = current.temp_c,
        wind_dir = current.wind_dir,
        wind_kph = current.wind_kph
    )
}