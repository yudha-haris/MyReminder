package com.example.myreminder.core.data.source.remote.network

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val errorMessage: String) : Result<Nothing>()
    object Empty : Result<Nothing>()
    object Loading : Result<Nothing>()
}