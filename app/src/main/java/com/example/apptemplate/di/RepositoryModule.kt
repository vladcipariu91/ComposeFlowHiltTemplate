package com.example.apptemplate.di

import com.example.feature.character_list.data.CharacterRepositoryImpl
import com.example.feature.character_list.domain.CharacterRepository
import com.example.feature.template.data.DeliveryRepositoryImpl
import com.example.feature.template.domain.DeliveryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCharactersRepository(repository: CharacterRepositoryImpl) : CharacterRepository

    @Binds
    abstract fun bindDeliveriesRepository(repository: DeliveryRepositoryImpl) : DeliveryRepository
}