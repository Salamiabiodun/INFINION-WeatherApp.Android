package com.example.weatherapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.splash.SplashScreen
import com.example.weatherapp.ui.weather.HomeScreen
import com.example.weatherapp.ui.weather.WeatherDetailsScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("details") { WeatherDetailsScreen() }
    }
}
