package com.rubikans.challenge.di

import com.rubikans.challenge.cache.source.CharactersCacheDataSourceImp
import com.rubikans.challenge.cache.source.SettingsCacheDataSourceImp
import com.rubikans.challenge.data.source.CharactersCacheDataSource
import com.rubikans.challenge.data.source.SettingsCacheDataSource
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

    @Binds
    @Singleton
    abstract fun bindSettingsCacheDataSource(
        settingsCacheDataSource: SettingsCacheDataSourceImp,
    ): SettingsCacheDataSource


}