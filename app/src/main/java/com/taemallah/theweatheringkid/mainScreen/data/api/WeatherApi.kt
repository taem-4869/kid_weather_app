package com.taemallah.theweatheringkid.mainScreen.data.api

import com.taemallah.theweatheringkid.constants.Consts
import com.taemallah.theweatheringkid.mainScreen.domain.models.weatherModel.WeatherDataDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("v1/current.json")
    suspend fun getWeatherData(
        @Query("q") city: String,
        @Query("key") apiKey: String= Consts.API_KEY
    ): Response<WeatherDataDto>
}