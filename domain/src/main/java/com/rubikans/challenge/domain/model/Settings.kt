package com.rubikans.challenge.domain.model

data class Settings(
    val id: Int,
    val type: SettingType,
    val settingLabel: String,
    val settingValue: String,
    var defaultValue: Boolean = false,
)

enum class SettingType {
    SWITCH,
    TEXT,
    EMPTY
}