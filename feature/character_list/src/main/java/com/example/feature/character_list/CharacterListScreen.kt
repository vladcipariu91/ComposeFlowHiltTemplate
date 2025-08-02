package com.example.feature.character_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
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
    LazyColumn(
        contentPadding = PaddingValues(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
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
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = character.image,
                placeholder = rememberVectorPainter(Icons.Default.Person),
                contentScale = ContentScale.Crop,
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = character.name)
                Text(text = character.species)
            }
        }
    }
}