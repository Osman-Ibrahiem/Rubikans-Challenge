package com.rubikans.challenge.core.theme

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatCheckBox
import com.rubikans.challenge.R

class ToggleThemeCheckBox @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatCheckBox(context, attrs) {

    init {
        setButtonDrawable(R.drawable.toggle_theme)
    }
}