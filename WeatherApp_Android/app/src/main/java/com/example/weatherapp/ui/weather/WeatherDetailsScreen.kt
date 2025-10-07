package com.example.weatherapp.ui.weather

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun WeatherDetailsScreen(
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val weatherState by viewModel.weatherState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (val state = weatherState) {
            is WeatherUiState.Success -> {
                val weather = state.weather
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "City: ${weather.name}")
                    Text(text = "Temperature: ${weather.main.temp}°C")
                    Text(text = "Description: ${weather.weather.first().description}")
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { viewModel.saveFavoriteCity(weather.name) }) {
                        Text("Save as Favorite")
                    }
                }
            }
            else -> {
                // Handle other states if necessary
            }
        }
    }
}
