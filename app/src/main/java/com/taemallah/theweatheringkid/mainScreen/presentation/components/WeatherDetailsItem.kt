package com.taemallah.theweatheringkid.mainScreen.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.taemallah.theweatheringkid.mainScreen.domain.models.weatherModel.WeatherData

@Composable
fun WeatherDetailsItem(
    item: WeatherDetailsItem
) {
    Column(
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.secondaryContainer,
                RoundedCornerShape(30)
            )
            .border(
                1.dp,
                Brush.verticalGradient(
                    listOf(
                        Color.Transparent,
                        MaterialTheme.colorScheme.primary
                    )
                ),
                RoundedCornerShape(30)
            )
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = item.name,
            style = MaterialTheme.typography.labelSmall
        )
        Spacer(
            modifier = Modifier
                .height(2.dp)
                .width(50.dp)
                .background(MaterialTheme.colorScheme.secondary)
        )
        Text(
            text = item.value,
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}

data class WeatherDetailsItem(
    val name: String,
    val value: String
)

fun getWeatherDetailsItems(weatherData: WeatherData):List<WeatherDetailsItem>{
    return listOf(
        WeatherDetailsItem("wind direction",weatherData.wind_dir),
        WeatherDetailsItem("wind speed","${weatherData.wind_kph}Km/h"),
        WeatherDetailsItem("humidity",weatherData.humidity.toString()),
        WeatherDetailsItem("pressure",weatherData.pressure_mb.toString()+"mb"),
        WeatherDetailsItem("cloud",weatherData.cloud.toString()),
    )
}

@Preview
@Composable
private fun WeatherDetailsItemPreview() {
    WeatherDetailsItem(WeatherDetailsItem("wind direction","Est"))
}