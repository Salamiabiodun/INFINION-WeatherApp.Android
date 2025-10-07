package com.example.weatherapp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import com.example.weatherapp.data.model.Main
import com.example.weatherapp.data.model.Weather
import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.data.network.WeatherApiService
import com.example.weatherapp.data.repository.WeatherRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class WeatherRepositoryTest {

    private lateinit var repository: WeatherRepository
    private val weatherApiService: WeatherApiService = mockk()
    private lateinit var dataStore: DataStore<Preferences>
    private val context: Context = ApplicationProvider.getApplicationContext()

    @Before
    fun setup() {
        dataStore = PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile("test_settings") }
        )
        repository = WeatherRepository(weatherApiService, context)
    }

    @Test
    fun `getWeather returns weather response`() = runTest {
        val weatherResponse = WeatherResponse(
            weather = listOf(Weather("clear sky")),
            main = Main(25.0),
            name = "London"
        )
        coEvery { weatherApiService.getWeather("London", any(), any()) } returns weatherResponse

        val result = repository.getWeather("London")

        assertEquals(weatherResponse, result)
    }

    @Test
    fun `save and get favorite city`() = runTest {
        repository.saveFavoriteCity("Paris")

        val favoriteCity = repository.favoriteCity.first()

        assertEquals("Paris", favoriteCity)
    }
}
