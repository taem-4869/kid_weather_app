package com.taemallah.theweatheringkid.mainScreen.data.repository

import androidx.compose.ui.res.stringResource
import com.taemallah.theweatheringkid.R
import com.taemallah.theweatheringkid.mainScreen.data.api.WeatherApi
import com.taemallah.theweatheringkid.mainScreen.data.mappers.toWeatherData
import com.taemallah.theweatheringkid.mainScreen.domain.models.weatherModel.WeatherData
import com.taemallah.theweatheringkid.mainScreen.domain.models.weatherModel.WeatherResponse
import com.taemallah.theweatheringkid.mainScreen.domain.repository.WeatherRepo
import javax.inject.Inject

class WeatherRepoImpl @Inject constructor(
    private val weatherApi: WeatherApi
) :WeatherRepo {
    override suspend fun getWeatherData(city : String): WeatherResponse {
        try {
            val response = weatherApi.getWeatherData(city)
            if (response.body()==null){
                return WeatherResponse.Failure("unexpected error\n${response.message()}")
            }else
                return WeatherResponse.Success(response.body()!!.toWeatherData())
        }catch (e : Exception){
            return WeatherResponse.Failure(e.message?:"unexpected error")
        }
    }
}