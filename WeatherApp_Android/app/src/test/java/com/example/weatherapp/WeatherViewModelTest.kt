package com.example.weatherapp

import com.example.weatherapp.data.model.Main
import com.example.weatherapp.data.model.Weather
import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.data.repository.WeatherRepository
import com.example.weatherapp.ui.weather.WeatherUiState
import com.example.weatherapp.ui.weather.WeatherViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class WeatherViewModelTest {

    private lateinit var viewModel: WeatherViewModel
    private val repository: WeatherRepository = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        coEvery { repository.favoriteCity } returns flowOf("London")
        viewModel = WeatherViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getWeather success`() = runTest {
        val weatherResponse = WeatherResponse(
            weather = listOf(Weather("clear sky")),
            main = Main(25.0),
            name = "London"
        )
        coEvery { repository.getWeather("London") } returns weatherResponse

        viewModel.getWeather("London")
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(WeatherUiState.Success(weatherResponse), viewModel.weatherState.value)
    }

    @Test
    fun `getWeather error`() = runTest {
        val errorMessage = "Error fetching weather"
        coEvery { repository.getWeather("London") } throws RuntimeException(errorMessage)

        viewModel.getWeather("London")
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(WeatherUiState.Error(errorMessage), viewModel.weatherState.value)
    }

    @Test
    fun `save favorite city`() = runTest {
        coEvery { repository.saveFavoriteCity("Paris") } returns Unit

        viewModel.saveFavoriteCity("Paris")
        testDispatcher.scheduler.advanceUntilIdle()

        coVerify { repository.saveFavoriteCity("Paris") }
    }
}
