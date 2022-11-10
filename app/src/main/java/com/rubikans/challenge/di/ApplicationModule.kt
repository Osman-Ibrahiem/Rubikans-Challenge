package com.rubikans.challenge.di

import com.rubikans.challenge.BuildConfig
import com.rubikans.challenge.remote.di.qualifiers.AppBuildType
import com.rubikans.challenge.remote.di.qualifiers.AppRemoteUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {

    @AppRemoteUrl
    @Provides
    fun provideBaseURl(): String {
        return "https://reqres.in/api/"
    }

    @AppBuildType
    @Provides
    fun provideBuildType(): Boolean {
        return BuildConfig.DEBUG
    }

}