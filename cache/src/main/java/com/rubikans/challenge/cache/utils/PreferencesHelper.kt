package com.rubikans.challenge.cache.utils

import android.content.SharedPreferences
import javax.inject.Inject

open class PreferencesHelper @Inject constructor(
    private val preferences: SharedPreferences,
) {

    companion object {
        private const val PREF_KEY_FIRST_TIME = "first_time"
        private const val PREF_KEY_NIGHT_MODE = "night_mode"
    }

    var isFirstTime: Boolean
        get() = preferences.getBoolean(PREF_KEY_FIRST_TIME, true)
        set(firstTime) = preferences.edit().putBoolean(PREF_KEY_FIRST_TIME, firstTime).apply()

    var isNightMode: Boolean
        get() = preferences.getBoolean(PREF_KEY_NIGHT_MODE, false)
        set(isDarkMode) = preferences.edit().putBoolean(PREF_KEY_NIGHT_MODE, isDarkMode).apply()

    fun clear() {
        preferences.edit().clear().apply()
    }
}