package com.rubikans.challenge.data.source

interface SettingsCacheDataSource {

    var isNight: Boolean
    val versionName: String

    fun clearCache()
}