package com.example.neworkingsecuritypersistence.networking

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A static object that we can use to set up our instance of Retrofit to perform networking with
 */
object WeatherRetrofitApiFactory {

    // An "Interceptor" allows us to "intercept" the current request before it is executed, then
    // add additional data to it, in this case, our weather API key

    // TODO: Register with the weather service: https://www.weatherapi.com/
    // TODO: Generate an API key from the weather service and update the below key with your own key
    private val apiKeyInterceptor = Interceptor { chain ->
        val httpUrl = chain.request().url().newBuilder()
            .addQueryParameter("key", "279e13ef7a514a629ac202121212103")
            .build()

        val request = chain.request().newBuilder()
            .url(httpUrl)
            .build()

        chain.proceed(request)
    }

    // Another Interceptor, this time we add logging details for the HTTP(s) requests
    private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    // We create an instance of OkHttpClient that we will use to build HTTP(s) requests
    private val okHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(apiKeyInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    // Setting up our instance of Retrofit which will actually perform our networking operations
    private fun buildRetrofit() = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://api.weatherapi.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    // WeatherApi is an interface built specifically to working with Retrofit based on our weather API service
    val weatherApi : WeatherApi = buildRetrofit().create(WeatherApi::class.java)
}