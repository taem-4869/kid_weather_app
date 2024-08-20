package com.taemallah.theweatheringkid.mainScreen.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.Coil
import coil.compose.AsyncImage
import com.taemallah.theweatheringkid.constants.Consts
import com.taemallah.theweatheringkid.mainScreen.presentation.WeatherEvent
import com.taemallah.theweatheringkid.mainScreen.presentation.WeatherState
import com.taemallah.theweatheringkid.mainScreen.presentation.getFormattedDAte

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    state: WeatherState,
    onEvent: (WeatherEvent)-> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 12.dp)
                .padding(vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(Consts.MAIN_SCREEN_SPACED_BY),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            val isVisibleDialogAnimation = MutableTransitionState(state.isVisibleCitySelector)
            AnimatedVisibility (isVisibleDialogAnimation){
                CitySelector(state = state,onEvent = onEvent)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        2.dp,
                        Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.primary
                            )
                        ),
                        RoundedCornerShape(30)
                    )
                    .padding(10.dp)
                    .animateContentSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onEvent(WeatherEvent.ShowCitySelector)
                        },
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
                    Text(
                        text = state.city.ifBlank { "Click to set location" },
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                if (state.data!=null){
                    Text(
                        text = "${state.data!!.location.country}, ${state.data!!.location.region}",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = state.data!!.location.localtime,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

            }
            AnimatedContent(targetState = state, label = "") { s->
                if(s.isSuccessfulRequest && s.data!=null){
                    DataDisplayLayer(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        state = s,
                        onEvent = onEvent
                    )
                }else{
                    Text(
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.error,
                        text = s.errorMessage,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DataDisplayLayer(modifier: Modifier= Modifier,state: WeatherState, onEvent: (WeatherEvent) -> Unit) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = state.data!!.condition.icon,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        )
        Column(Modifier.fillMaxWidth()) {
            Text(
                text = "${state.data!!.temp_c}°C",
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = state.data!!.condition.text,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = "feels like ${state.data!!.feelslike_c}°C",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier
            .width(40.dp)
            .height(2.dp)
            .background(Color.Gray))
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val items = getWeatherDetailsItems(state.data!!)
            items(items.count()){position->
                WeatherDetailsItem(item = items[position])
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen(WeatherState()){}
}