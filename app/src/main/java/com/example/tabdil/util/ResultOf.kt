package com.example.tabdil.util

sealed class ResultOf<out R> {

    data class Success<out T>(val data: T) : ResultOf<T>()
    data class ErrorEmptyLocal(val exception: Exception) : ResultOf<Nothing>()
    data class ErrorFillLocal(val exception: Exception) : ResultOf<Nothing>()
    object LoadingEmptyLocal : ResultOf<Nothing>()
    object LoadingFillLocal: ResultOf<Nothing>()
}
