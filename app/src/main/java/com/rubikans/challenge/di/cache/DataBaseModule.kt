package com.rubikans.challenge.di.cache

import android.content.Context
import androidx.room.Room
import com.rubikans.challenge.cache.database.CharactersDataBase
import com.rubikans.challenge.di.annotations.qualifiers.AppContext
import com.rubikans.challenge.di.annotations.qualifiers.AppDatabaseName
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
        @AppDatabaseName databaseName: String,
    ): CharactersDataBase {
        return Room.databaseBuilder(context, CharactersDataBase::class.java, databaseName).build()
    }

}