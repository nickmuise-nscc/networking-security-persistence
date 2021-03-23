package com.example.neworkingsecuritypersistence.persistence

import android.content.Context
import com.example.neworkingsecuritypersistence.models.CurrentWeather
import com.google.gson.Gson

/**
 * A class that lets us read and write some basic data to and from local storage called "shared preferences"
 */
class SharedPreferencesManager(context: Context) {
    // we create our shared prefs object
    private val sharedPrefs = context.getSharedPreferences("weatherSharedPrefs", Context.MODE_PRIVATE)

    // we write methods that allow us to perform read/write operations
    fun saveCurrentWeatherData(currentWeather: CurrentWeather) {
        val editor = sharedPrefs.edit()
        val currentWeatherGson = Gson().toJson(currentWeather)
        editor.putString(CURRENT_WEATHER_KEY, currentWeatherGson)
        editor.apply()
    }

    fun getCurrentWeatherData(): CurrentWeather? {
        val currentWeatherJson = sharedPrefs.getString(CURRENT_WEATHER_KEY, null)
        return Gson().fromJson(currentWeatherJson, CurrentWeather::class.java)
    }

    companion object {
        // we use keys for reading and writing data to/from shared preferences
        private const val CURRENT_WEATHER_KEY = "currentWeatherKey"
    }
}