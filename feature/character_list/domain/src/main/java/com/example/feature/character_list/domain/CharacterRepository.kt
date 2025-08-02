package com.example.feature.character_list.domain

import com.example.core.network.ApiResult
import com.example.feature.character_list.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharacters(): Flow<ApiResult<List<Character>>>
}