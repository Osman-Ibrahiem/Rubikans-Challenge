package com.rubikans.challenge.di.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rubikans.challenge.di.ApplicationModule
import com.rubikans.challenge.di.annotations.qualifiers.AppRemoteUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(includes = [OkHttpClientModule::class, ApplicationModule::class])
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        @AppRemoteUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(
        gson: Gson,
    ): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
            .setLenient()
        return gsonBuilder.create()
    }

}