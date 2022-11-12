package com.rubikans.challenge.di.cache

import android.content.Context
import android.content.SharedPreferences
import com.rubikans.challenge.cache.utils.PreferencesHelper
import com.rubikans.challenge.di.annotations.qualifiers.AppContext
import com.rubikans.challenge.di.annotations.qualifiers.AppPreferenceName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Provides
    @Singleton
    fun providePreference(
        @AppContext context: Context,
        @AppPreferenceName preferenceName: String,
    ): SharedPreferences {
        return context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferenceHelper(
        preferences: SharedPreferences,
    ): PreferencesHelper {
        return PreferencesHelper(preferences)
    }
}