package com.natasha.pockemon.utils.extensions

import com.natasha.pockemon.utils.UiState

fun <T> Result<T>.getFailureValue() = this.exceptionOrNull()?.message

fun <T> Result<T>.getThrowable(): Throwable = exceptionOrNull() ?: Exception()

fun <T> Result<T>.toUIState(): UiState<T> = fold(
    onSuccess = {
        UiState.Success(it)
    },
    onFailure = {
        UiState.Error(this.getFailureValue())
    }
)