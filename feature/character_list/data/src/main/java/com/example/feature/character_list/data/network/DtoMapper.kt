package com.example.feature.character_list.data.network

import com.example.feature.character_list.data.network.model.CharacterContainerDto
import com.example.feature.character_list.domain.model.Character

fun CharacterContainerDto.toCharacters(): List<Character> =
    this.characters.map { character ->
        Character(
            id = character.id,
            name = character.name,
            species = character.species,
            image = character.image
        )
    }