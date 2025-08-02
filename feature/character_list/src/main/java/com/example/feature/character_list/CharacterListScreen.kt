package com.example.feature.character_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.ui.state.UiState
import com.example.feature.character_list.domain.model.Character

@Composable
fun CharacterListScreen(
    modifier: Modifier = Modifier,
    viewModel: CharactersViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    val currentState: UiState<List<Character>> = uiState.value

    Box(modifier = modifier.fillMaxSize()) {
        when (currentState) {
            is UiState.Success -> CharacterList(characters = currentState.data)
            is UiState.Error -> Text(text = "Something went wrong: ${currentState.message}")
            is UiState.Loading -> Text(text = "Loading...")
        }
    }


}

@Composable
fun CharacterList(
    characters: List<Character>,
    modifier: Modifier = Modifier
) {
    LazyColumn() {
        items(characters.size) {
            CharacterItem(characters[it])
        }
    }
}

@Composable
fun CharacterItem(
    character: Character,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Column {
            Text(text = character.name)
            Text(text = character.species)
        }
    }
}