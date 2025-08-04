package com.example.feature.template

import com.example.feature.template.domain.Delivery

sealed class UiState<out T> {
    data object Loading: UiState<Nothing>()
    data class Success(val data: List<Delivery>) : UiState<List<Delivery>>()
}