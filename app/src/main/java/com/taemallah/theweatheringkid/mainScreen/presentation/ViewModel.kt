package com.taemallah.theweatheringkid.mainScreen.presentation

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taemallah.theweatheringkid.mainScreen.domain.models.weatherModel.WeatherResponse
import com.taemallah.theweatheringkid.mainScreen.domain.repository.WeatherPreferencesRepo
import com.taemallah.theweatheringkid.mainScreen.domain.repository.WeatherRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class ViewModel @Inject constructor(
    private val weatherRepo: WeatherRepo,
    private val weatherPreferencesRepo: WeatherPreferencesRepo
) : ViewModel() {
    private var _state = mutableStateOf(WeatherState())
    val state = _state

    init {
        onEvent(WeatherEvent.LoadCityFromPreferences)
    }

    fun onEvent(event: WeatherEvent){
        when(event){
            WeatherEvent.LoadWeatherData -> viewModelScope.launch {
                when(val response = weatherRepo.getWeatherData(_state.value.city)){
                    is WeatherResponse.Failure -> {
                        _state.value = _state.value.copy(
                            data = null,
                            errorMessage = response.message,
                            isSuccessfulRequest = false
                        )
                    }
                    is WeatherResponse.Success -> {
                        _state.value = _state.value.copy(
                            isSuccessfulRequest = true,
                            data = response.data,
                            errorMessage = "")
                    }
                }
            }
            WeatherEvent.HideCitySelector -> {
                _state.value = _state.value.copy(isVisibleCitySelector = false)
            }
            WeatherEvent.ShowCitySelector -> {
                _state.value = _state.value.copy(isVisibleCitySelector = true)
            }
            WeatherEvent.LoadCityFromPreferences -> {
                viewModelScope.launch {
                    weatherPreferencesRepo.getCity().collect{
                        _state.value = _state.value.copy(city = it)
                        onEvent(WeatherEvent.LoadWeatherData)
                    }
                }
            }
            is WeatherEvent.SaveCityToPreferences -> {
                viewModelScope.launch {
                    val capitalizedCity = event.city.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.ROOT
                        ) else it.toString()
                    }
                    weatherPreferencesRepo.saveCity(capitalizedCity)
                    onEvent(WeatherEvent.LoadCityFromPreferences)
                }
            }
        }
    }
}