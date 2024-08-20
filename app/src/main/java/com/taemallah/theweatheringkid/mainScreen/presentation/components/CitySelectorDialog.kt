package com.taemallah.theweatheringkid.mainScreen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.taemallah.theweatheringkid.constants.Consts
import com.taemallah.theweatheringkid.mainScreen.presentation.WeatherEvent
import com.taemallah.theweatheringkid.mainScreen.presentation.WeatherState

@Composable
fun CitySelector(state: WeatherState, onEvent: (WeatherEvent) -> Unit) {
    Dialog(onDismissRequest = { onEvent(WeatherEvent.HideCitySelector) }) {
        var city by remember {
            mutableStateOf(state.city)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.background.copy(alpha = .9f),
                    RoundedCornerShape(30)
                )
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Set Location",
                style = MaterialTheme.typography.titleLarge
            )
            val localSoftwareKeyboardController = LocalSoftwareKeyboardController.current
            OutlinedTextField(
                modifier = Modifier.focusTarget(),
                value = city,
                onValueChange = { city=it },
                label = { Text(text = "your city") },
                textStyle = MaterialTheme.typography.titleSmall,
                leadingIcon = { Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)},
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Go),
                keyboardActions = KeyboardActions(
                    onGo = {
                        onEvent(WeatherEvent.SaveCityToPreferences(city))
                        onEvent(WeatherEvent.HideCitySelector)
                        localSoftwareKeyboardController?.hide()
                    }
                )
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(.9f),
                    onClick = {
                        onEvent(WeatherEvent.SaveCityToPreferences(city))
                        onEvent(WeatherEvent.HideCitySelector)
                    }) {
                    Text(text = "Save")
                }
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(.9f),
                    onClick = {
                        onEvent(WeatherEvent.HideCitySelector)
                    }) {
                    Text(text = "Cancel")
                }
            }
        }
    }
}
