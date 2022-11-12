package com.rubikans.challenge.domain.model

data class Settings(
    val id: Int,
    val type: SettingType,
    val settingLabel: String,
    val settingValue: String,
    var selectedValue: Boolean = false,
)

enum class SettingType {
    SWITCH,
    TEXT,
    EMPTY
}

const val ID_SETTINGS_THEME = 1
const val ID_SETTINGS_CLEAR_CACHE = 2
const val ID_SETTINGS_VERSION = 3