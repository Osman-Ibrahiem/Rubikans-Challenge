package com.rubikans.challenge.data.di

import com.rubikans.challenge.data.repository.CharacterRepositoryImp
import com.rubikans.challenge.domain.repository.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBinderModule {

    @Binds
    abstract fun bindCharacterRepository(characterRepository: CharacterRepositoryImp): CharacterRepository


}