package com.example.feature.template

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature.template.domain.Delivery
import com.example.feature.template.domain.DeliveryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeliveriesViewModel @Inject constructor(repository: DeliveryRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Delivery>>>(UiState.Loading)
    val uiState: StateFlow<UiState<*>> = _uiState

    init {
        viewModelScope.launch {
            repository.getDeliveries().collect { result ->
                _uiState.value = UiState.Success(result)
            }
        }
    }
}