package com.rubikans.challenge.remote.di

import com.rubikans.challenge.data.source.CharactersRemoteDataSource
import com.rubikans.challenge.remote.source.CharactersRemoteDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteBinderModule {

    @Binds
    abstract fun bindCharactersRemoteDataSource(charactersRemoteDataSource: CharactersRemoteDataSourceImp): CharactersRemoteDataSource


}