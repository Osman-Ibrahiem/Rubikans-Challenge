package com.rubikans.challenge.core.theme

import android.content.Context
import androidx.fragment.app.FragmentActivity

interface ThemeUtils {
    fun isDarkTheme(context: Context): Boolean
    fun isLightTheme(context: Context): Boolean
    fun setNightMode(activity: FragmentActivity, nightMode: Boolean)
    fun setNightMode(nightMode: Boolean)
}