package com.rubikans.challenge

import android.app.Application
import com.rubikans.challenge.cache.utils.PreferencesHelper
import com.rubikans.challenge.core.theme.ThemeUtils
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class RubikansChallengeApplication : Application() {

    @Inject
    lateinit var themeUtils: ThemeUtils

    @Inject
    lateinit var preferencesHelper: PreferencesHelper

    override fun onCreate() {
        super.onCreate()
        initNightMode()
    }

    /**
     * Initialize Night Mode based on user last saved state (day/night themes).
     */
    private fun initNightMode() {
        val isFirstTime = preferencesHelper.isFirstTime
        if (isFirstTime) {
            preferencesHelper.isFirstTime = false
            preferencesHelper.isNightMode = themeUtils.isDarkTheme(this)
        }
        themeUtils.setNightMode(preferencesHelper.isNightMode)
    }
}