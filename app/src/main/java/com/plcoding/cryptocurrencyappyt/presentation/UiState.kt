package com.plcoding.cryptocurrencyappyt.presentation

data class UiState<T> (
    val isLoading: Boolean = false,
    val data: T? = null,
    val error: String = ""
        )