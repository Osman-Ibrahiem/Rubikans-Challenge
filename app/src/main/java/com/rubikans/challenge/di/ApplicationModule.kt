package com.rubikans.challenge.di

import android.app.Application
import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.rubikans.challenge.BuildConfig
import com.rubikans.challenge.R
import com.rubikans.challenge.core.theme.ThemeUtils
import com.rubikans.challenge.core.theme.ThemeUtilsImp
import com.rubikans.challenge.di.annotations.qualifiers.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


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

    @AppDatabaseName
    @Provides
    fun provideDatabaseName(): String {
        return BuildConfig.DATABASE_NAME
    }

    @AppPreferenceName
    @Provides
    fun providePreferenceName(): String {
        return BuildConfig.PREFERENCES_NAME
    }

    @AppBuildType
    @Provides
    fun provideBuildType(): Boolean {
        return BuildConfig.DEBUG
    }

    @AppVersionName
    @Provides
    fun provideVersionName(): String {
        return BuildConfig.VERSION_NAME
    }

    @Singleton
    @Provides
    fun provideGlideInstance(
        @AppContext context: Context,
    ) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
        )

    @Singleton
    @Provides
    fun bindThemeUtils(themeUtilsImp: ThemeUtilsImp): ThemeUtils = themeUtilsImp
}