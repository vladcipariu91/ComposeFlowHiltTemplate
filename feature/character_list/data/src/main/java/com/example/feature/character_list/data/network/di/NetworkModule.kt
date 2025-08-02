package com.example.feature.character_list.data.network.di

import com.example.feature.character_list.data.network.CharacterApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    
    @Provides
    fun providesCharacterApiService(retrofit: Retrofit): CharacterApiService =
        retrofit.create(CharacterApiService::class.java)
}