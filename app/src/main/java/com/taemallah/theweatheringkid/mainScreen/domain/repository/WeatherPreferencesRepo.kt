package com.taemallah.theweatheringkid.mainScreen.domain.repository

import kotlinx.coroutines.flow.Flow


interface WeatherPreferencesRepo{

    suspend fun saveCity(city: String)

    suspend fun getCity(): Flow<String>
}