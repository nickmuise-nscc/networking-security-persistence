package com.example.neworkingsecuritypersistence.networking

import com.example.neworkingsecuritypersistence.models.CurrentWeather

/**
 * This is the main way we are going to interact with the weather api from our application code
 * We write this class that interfaces with the Retrofit interface we wrote "WeatherApi.kt"
 */
class WeatherRepository(private val weatherApi: WeatherApi) : BaseRepository() {

    suspend fun getCurrentWeather(location: String): CurrentWeather? {
        return safeApiCall(
        call = { weatherApi.getCurrentWeatherForLocationAsync(location).await() },
        errorMessage = "Error fetching current weather at location: $location"
        )
    }

    // TODO: Update this function to call your newly added function in the WeatherApi interface for
    // TODO: fetching the forecast. Hint: you'll need to pass in the required values that this endpoint needs
    // check the documentation: https://www.weatherapi.com/api-explorer.aspx
//    suspend fun getForecast(): Any? {
//        return safeApiCall(
//            call = { },
//            errorMessage = "Error fetching forecast"
//        )
//    }
}