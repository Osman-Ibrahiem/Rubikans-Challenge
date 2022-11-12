package com.rubikans.challenge.cache.source

import com.rubikans.challenge.cache.utils.PreferencesHelper
import com.rubikans.challenge.data.source.SettingsCacheDataSource
import com.rubikans.challenge.di.annotations.qualifiers.AppVersionName
import javax.inject.Inject

class SettingsCacheDataSourceImp @Inject constructor(
    @AppVersionName private val _versionName: String,
    private val preferences: PreferencesHelper,
) : SettingsCacheDataSource {

    override var isNight: Boolean
        get() = preferences.isNightMode
        set(isDarkMode) {
            preferences.isNightMode = isDarkMode
        }

    override val versionName: String
        get() = _versionName

}