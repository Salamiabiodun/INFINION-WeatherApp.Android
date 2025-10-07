package com.example.weatherapp.ui.weather

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val weatherState by viewModel.weatherState.collectAsState()
    val favoriteCity by viewModel.favoriteCity.collectAsState(initial = null)
    var city by remember { mutableStateOf("") }

    LaunchedEffect(favoriteCity) {
        favoriteCity?.let {
            city = it
        }
    }

    LaunchedEffect(weatherState) {
        if (weatherState is WeatherUiState.Success) {
            navController.navigate("details")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("Enter city") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.getWeather(city) }) {
            Text("Get Weather")
        }
        Spacer(modifier = Modifier.height(16.dp))
        when (weatherState) {
            is WeatherUiState.Loading -> CircularProgressIndicator()
            is WeatherUiState.Error -> {
                val error = (weatherState as WeatherUiState.Error).message
                Text(text = "Error: $error")
            }
            else -> {}
        }
    }
}
