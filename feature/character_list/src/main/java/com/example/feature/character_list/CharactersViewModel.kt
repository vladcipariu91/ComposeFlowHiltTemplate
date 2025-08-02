package com.example.feature.character_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.network.ApiResult
import com.example.core.ui.state.UiState
import com.example.feature.character_list.domain.CharacterRepository
import com.example.feature.character_list.domain.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    val uiState: StateFlow<UiState<List<Character>>> =
        repository.getCharacters()
            .map { apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> {
                        val characters: List<Character> = apiResult.data ?: emptyList()
                        UiState.Success<List<Character>>(characters)
                    }

                    is ApiResult.Error -> UiState.Error(apiResult.message)
                    is ApiResult.Loading -> UiState.Loading
                }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = UiState.Loading
            )


}