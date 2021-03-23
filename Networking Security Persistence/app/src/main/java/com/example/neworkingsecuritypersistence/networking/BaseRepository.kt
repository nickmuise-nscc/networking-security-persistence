package com.example.neworkingsecuritypersistence.networking

import android.util.Log
import retrofit2.Response
import java.io.IOException

/**
 * Based off of the following development article on Medium.com:
 * https://medium.com/android-news/android-networking-in-2019-retrofit-with-kotlins-coroutines-aefe82c4d777
 *
 * Original Author: Navendra Jha, Jan 13, 2019
 */
open class BaseRepository{

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {

        val result : Result<T> = safeApiResult(call,errorMessage)
        var data : T? = null

        when(result) {
            is Result.Success ->
                data = result.data
            is Result.Error -> {
                Log.d("1.DataRepository", "$errorMessage & Exception - ${result.exception}")
            }
        }

        return data
    }

    private suspend fun <T: Any> safeApiResult(call: suspend ()-> Response<T>, errorMessage: String) : Result<T>{
        val response = call.invoke()
        if(response.isSuccessful) return Result.Success(response.body()!!)

        return Result.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
    }
}