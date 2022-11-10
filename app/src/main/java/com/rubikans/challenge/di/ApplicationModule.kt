package com.rubikans.challenge.di

import android.app.Application
import android.content.Context
import com.rubikans.challenge.BuildConfig
import com.rubikans.challenge.di.annotations.qualifiers.AppBuildType
import com.rubikans.challenge.di.annotations.qualifiers.AppContext
import com.rubikans.challenge.di.annotations.qualifiers.AppRemoteUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {

    @AppContext
    @Provides
    fun context(application: Application): Context {
        return application.applicationContext
    }

    @AppRemoteUrl
    @Provides
    fun provideBaseURl(): String {
        return BuildConfig.BASE_URL + "/api/"
    }

    @AppBuildType
    @Provides
    fun provideBuildType(): Boolean {
        return BuildConfig.DEBUG
    }

}