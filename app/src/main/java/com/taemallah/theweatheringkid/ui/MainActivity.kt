package com.taemallah.theweatheringkid.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.taemallah.theweatheringkid.mainScreen.presentation.ViewModel
import com.taemallah.theweatheringkid.mainScreen.presentation.components.MainScreen
import com.taemallah.theweatheringkid.ui.theme.TheWeatheringKIDTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel : ViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheWeatheringKIDTheme {
                MainScreen(viewModel.state.value,viewModel::onEvent)
            }
        }
    }
}