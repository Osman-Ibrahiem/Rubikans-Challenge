package com.rubikans.challenge.di

import com.rubikans.challenge.cache.dao.CharacterDao
import com.rubikans.challenge.cache.database.CharactersDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheProviderModule {

    @Provides
    @Singleton
    fun provideCharacterDao(
        dataBase: CharactersDataBase,
    ): CharacterDao = dataBase.cachedCharacterDao()

}