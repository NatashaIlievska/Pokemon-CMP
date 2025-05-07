package com.natasha.pockemon.utils

sealed class UiState<out R> {
    data object Loading : UiState<Nothing>()
    data class Success<out T>(val data: T?) : UiState<T>()
    data class Error(val message: String?) : UiState<Nothing>()

    fun getSuccessData() = (this as? Success)?.data
}
