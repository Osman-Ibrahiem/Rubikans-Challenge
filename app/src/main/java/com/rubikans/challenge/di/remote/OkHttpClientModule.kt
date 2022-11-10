package com.rubikans.challenge.di.remote

import android.content.Context
import com.rubikans.challenge.di.ApplicationModule
import com.rubikans.challenge.di.annotations.qualifiers.AppBuildType
import com.rubikans.challenge.di.annotations.qualifiers.AppContext
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module(includes = [ApplicationModule::class])
object OkHttpClientModule {

    private const val OK_HTTP_TIMEOUT = 120L


    @Provides
    fun provideOkHttpClient(
        @AppContext context: Context,
        authorizationInterceptor: AuthorizationInterceptor,
        responseInterceptor: ResponseInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
//            .cache(cache)
            .connectTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(authorizationInterceptor)
            .addInterceptor(responseInterceptor)
            .build()
    }

    @Provides
    fun provideHttpLoggingInterceptor(
        @AppBuildType isDebug: Boolean,
    ): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (isDebug) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    fun provideCache(
        @AppContext context: Context,
    ): Cache {
        val cacheSize = (5 * 1024 * 1024).toLong()
        return Cache(context.cacheDir, cacheSize)
    }
}