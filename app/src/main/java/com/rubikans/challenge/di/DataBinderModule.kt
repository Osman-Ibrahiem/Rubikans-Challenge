package com.rubikans.challenge.di

import com.rubikans.challenge.data.repository.CharacterRepositoryImp
import com.rubikans.challenge.data.repository.SettingsRepositoryImp
import com.rubikans.challenge.domain.repository.CharacterRepository
import com.rubikans.challenge.domain.repository.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBinderModule {

    @Binds
    abstract fun bindCharacterRepository(characterRepository: CharacterRepositoryImp): CharacterRepository

    @Binds
    abstract fun bindSettingRepository(settingsRepository: SettingsRepositoryImp): SettingsRepository
}