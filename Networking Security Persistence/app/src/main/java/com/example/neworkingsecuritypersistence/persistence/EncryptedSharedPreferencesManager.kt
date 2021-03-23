package com.example.neworkingsecuritypersistence.persistence

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.neworkingsecuritypersistence.models.CurrentWeather
import com.google.gson.Gson

class EncryptedSharedPreferencesManager(context: Context) {
    private val key = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val encryptedSharedPrefs = EncryptedSharedPreferences.create(
        context,
        "encryptedWeatherSharedPrefs",
        key,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveCurrentWeatherData(currentWeather: CurrentWeather) {
        val editor = encryptedSharedPrefs.edit()
        val currentWeatherGson = Gson().toJson(currentWeather)
        editor.putString(CURRENT_WEATHER_KEY, currentWeatherGson)
        editor.apply()
    }

    fun getCurrentWeatherData(): CurrentWeather? {
        val currentWeatherJson = encryptedSharedPrefs.getString(CURRENT_WEATHER_KEY, null)
        return Gson().fromJson(currentWeatherJson, CurrentWeather::class.java)
    }

    companion object {
        private const val CURRENT_WEATHER_KEY = "currentWeatherKey"
    }
}