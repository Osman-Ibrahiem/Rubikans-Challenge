package com.rubikans.challenge.data.repository

import com.rubikans.challenge.data.source.SettingsCacheDataSource
import com.rubikans.challenge.domain.model.*
import com.rubikans.challenge.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SettingsRepositoryImp @Inject constructor(
    private val dataSource: SettingsCacheDataSource,
) : SettingsRepository {
    override suspend fun getSettings(): Flow<List<Settings>> = flow {
        emit(getData())
    }

    //This should be came from api but we don't have api so we are crating locally
    private fun getData(): List<Settings> {
        return listOf(
            Settings(
                id = ID_SETTINGS_THEME,
                type = SettingType.SWITCH,
                settingLabel = "Theme mode",
                settingValue = "",
                selectedValue = isNight,
            ),
            Settings(
                id = ID_SETTINGS_CLEAR_CACHE,
                type = SettingType.EMPTY,
                settingLabel = "Clear cache",
                settingValue = "",
            ),
            Settings(
                id = ID_SETTINGS_VERSION,
                type = SettingType.TEXT,
                settingLabel = "App version",
                settingValue = dataSource.versionName,
            ),
        )
    }

    override var isNight: Boolean
        get() = dataSource.isNight
        set(isDarkMode) {
            dataSource.isNight = isDarkMode
        }

    override fun clearCache() = dataSource.clearCache()
}