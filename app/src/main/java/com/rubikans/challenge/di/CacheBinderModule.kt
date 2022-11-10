package com.rubikans.challenge.di

import com.rubikans.challenge.cache.source.CharactersCacheDataSourceImp
import com.rubikans.challenge.data.source.CharactersCacheDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheBinderModule {

    @Binds
    @Singleton
    abstract fun bindCharactersCacheDataSource(
        charactersCacheDataSource: CharactersCacheDataSourceImp,
    ): CharactersCacheDataSource


}