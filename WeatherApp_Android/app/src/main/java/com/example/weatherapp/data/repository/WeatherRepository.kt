package com.example.weatherapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.data.network.WeatherApiService
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class WeatherRepository @Inject constructor(
    private val weatherApiService: WeatherApiService,
    @ApplicationContext private val context: Context
) {
    private val apiKey = "898a18b6ed2db12e749434ee944634f4"

    suspend fun getWeather(city: String): WeatherResponse {
        return weatherApiService.getWeather(city, apiKey)
    }

    private val favoriteCityKey = stringPreferencesKey("favorite_city")

    val favoriteCity: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[favoriteCityKey]
        }

    suspend fun saveFavoriteCity(city: String) {
        context.dataStore.edit { settings ->
            settings[favoriteCityKey] = city
        }
    }
}
