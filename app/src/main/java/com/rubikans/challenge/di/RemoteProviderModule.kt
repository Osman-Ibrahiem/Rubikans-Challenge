package com.rubikans.challenge.di

import com.rubikans.challenge.remote.api.CharacterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteProviderModule {

    @Provides
    @Singleton
    fun provideCharacterService(
        retrofit: Retrofit,
    ): CharacterService = retrofit.create(CharacterService::class.java)

}