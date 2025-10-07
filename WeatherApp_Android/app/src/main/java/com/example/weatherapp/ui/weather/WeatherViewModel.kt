package com.example.weatherapp.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _weatherState = MutableStateFlow<WeatherUiState>(WeatherUiState.Empty)
    val weatherState: StateFlow<WeatherUiState> = _weatherState.asStateFlow()

    val favoriteCity = repository.favoriteCity

    fun getWeather(city: String) {
        viewModelScope.launch {
            _weatherState.value = WeatherUiState.Loading
            try {
                val weather = repository.getWeather(city)
                _weatherState.value = WeatherUiState.Success(weather)
            } catch (e: Exception) {
                _weatherState.value = WeatherUiState.Error(e.message ?: "An error occurred")
            }
        }
    }

    fun saveFavoriteCity(city: String) {
        viewModelScope.launch {
            repository.saveFavoriteCity(city)
        }
    }
}

sealed class WeatherUiState {
    object Empty : WeatherUiState()
    object Loading : WeatherUiState()
    data class Success(val weather: WeatherResponse) : WeatherUiState()
    data class Error(val message: String) : WeatherUiState()
}
