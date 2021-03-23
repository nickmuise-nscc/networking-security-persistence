package com.example.neworkingsecuritypersistence.networking

import com.example.neworkingsecuritypersistence.models.CurrentWeather
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * An interface used by Retrofit. Here is where we define our endpoints and what our HTTP(s) requests
 * look with query params etc.
 */
interface WeatherApi {
    @GET("current.json")
    fun getCurrentWeatherForLocationAsync(@Query("q") location: String): Deferred<Response<CurrentWeather>>

    // TODO: Add function for making a GET request to the "forecast.json" endpoint. The return type
    // TODO: of the function should be Deferred<Response<Any>>
    // https://www.weatherapi.com/api-explorer.aspx
}