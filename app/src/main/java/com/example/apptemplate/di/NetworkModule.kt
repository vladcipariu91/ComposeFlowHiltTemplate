package com.example.apptemplate.di

import com.example.feature.character_list.data.CharacterRepositoryImpl
import com.example.feature.character_list.data.network.CharacterApiService
import com.example.feature.character_list.domain.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun providesRetrofit(): Retrofit {
        val json = Json {
            ignoreUnknownKeys = true // crucial for real-world APIs!
            isLenient = true
            coerceInputValues = true
        }

        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(json.asConverterFactory(contentType = "application/json".toMediaType()))
            .build()
    }

    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}