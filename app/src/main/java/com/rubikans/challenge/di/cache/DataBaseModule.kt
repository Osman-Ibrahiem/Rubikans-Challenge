package com.rubikans.challenge.di.cache

import android.content.Context
import com.rubikans.challenge.cache.database.CharactersDataBase
import com.rubikans.challenge.cache.utils.PreferencesHelper
import com.rubikans.challenge.di.annotations.qualifiers.AppContext
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideCharactersDataBase(
        @AppContext context: Context,
    ): CharactersDataBase {
        return CharactersDataBase.getInstance(context)
    }

    @Provides
    @Singleton
    fun providePreferenceHelper(
        @AppContext context: Context,
    ): PreferencesHelper {
        return PreferencesHelper(context)
    }
}