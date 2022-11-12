package com.rubikans.challenge.data.repository

import com.rubikans.challenge.di.annotations.qualifiers.AppVersionName
import com.rubikans.challenge.domain.model.SettingType
import com.rubikans.challenge.domain.model.Settings
import com.rubikans.challenge.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SettingsRepositoryImp @Inject constructor(
    @AppVersionName private val appVersion: String,
) : SettingsRepository {
    override suspend fun getSettings(): Flow<List<Settings>> = flow {
        emit(getData())
    }

    //This should be came from api but we don't have api so we are crating locally
    private fun getData(): List<Settings> {
        val settingList = mutableListOf<Settings>()
        settingList.add(Settings(1, SettingType.SWITCH, "Theme mode", "", true))
        settingList.add(Settings(2, SettingType.EMPTY, "Clear cache", ""))
        settingList.add(Settings(2, SettingType.TEXT, "App version", appVersion))
        return settingList
    }

}