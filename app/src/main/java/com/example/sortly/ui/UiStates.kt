package com.example.sortly.ui

import com.example.sortly.data.model.Items

sealed class UiStates {
    data class Loading(val isLoading: Boolean) : UiStates()
    data class Success(val data: List<Items>): UiStates()
    data class Error(val errorMessage: String): UiStates()
}