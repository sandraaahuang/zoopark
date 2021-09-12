package com.sample.zoopark.data

import java.lang.Exception

sealed class Result<out T> {
    data class Success<out T>(val data: T): Result<T>()
    data class Fail<out T>(val error: T): Result<Nothing>()
    data class Error(val exception: Exception): Result<Nothing>()
    object Loading: Result<Nothing>()
}