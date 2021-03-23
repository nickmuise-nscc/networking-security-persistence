package com.example.neworkingsecuritypersistence.networking

import java.lang.Exception

/**
 * Classes representing the result from an API call, it is either going to succeed and give us a value,
 * or it is going to fail and we get nothing
 */

sealed class Result<out T: Any> {
    data class Success<out T: Any>(val data: T): Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}