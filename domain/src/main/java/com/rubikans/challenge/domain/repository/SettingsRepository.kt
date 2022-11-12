package com.rubikans.challenge.domain.repository

import com.rubikans.challenge.domain.model.Settings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    suspend fun getSettings(): Flow<List<Settings>>

    var isNight: Boolean

    fun clearCache()
}