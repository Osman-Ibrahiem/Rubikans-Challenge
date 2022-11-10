package com.rubikans.challenge.presentation.viewmodel

import androidx.annotation.StringRes
import com.rubikans.challenge.domain.model.CharactersList

sealed class CharacterState {

    object Init : CharacterState()

    object Loading : CharacterState()

    data class Error(@StringRes var message: Int) : CharacterState()

    data class Success(var characters: CharactersList) : CharacterState()
}
