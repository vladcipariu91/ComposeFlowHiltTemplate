package com.example.feature.character_list.data.network

import com.example.feature.character_list.data.network.model.CharacterContainerDto
import retrofit2.Response
import retrofit2.http.GET

interface CharacterApiService {

    @GET("character")
    suspend fun getCharacters(): Response<CharacterContainerDto>
}