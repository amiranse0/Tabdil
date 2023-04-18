package com.example.tabdil.util

sealed class ResultOf<out R> {
    data class Success<out T>(val data: T) : ResultOf<T>()
    data class Error(val exception: Exception) : ResultOf<Nothing>()
    object Loading: ResultOf<Nothing>()
}
