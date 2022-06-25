package com.example.wallpapercatalog.ui.model

sealed class UiState<out T : Any> {
    object Loading : UiState<Nothing>()
    data class Success<out T : Any>(val value: T) : UiState<T>()
    data class Error(val msg: String) : UiState<Nothing>()
}
