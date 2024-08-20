package com.taemallah.theweatheringkid.mainScreen.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.taemallah.theweatheringkid.constants.Consts
import com.taemallah.theweatheringkid.mainScreen.domain.repository.WeatherPreferencesRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WeatherPreferencesRepoImpl @Inject constructor (context: Context) : WeatherPreferencesRepo {

    val Context.dataStore : DataStore<Preferences> by preferencesDataStore(Consts.WEATHER_PREFERENCES_NAME)
    val pref = context.dataStore

    override suspend fun saveCity(city: String) {
        pref.edit {
            it[CITY_NAME]=city
        }
    }

    override suspend fun getCity(): Flow<String> = pref.data.map { it[CITY_NAME]?:"" }

    companion object {
        var CITY_NAME = stringPreferencesKey("city_name")
    }
}