package com.rubikans.challenge.presentation.viewmodel

import com.rubikans.challenge.domain.model.Character

internal sealed class CharacterState {

    object Init : CharacterState()

    object Loading : CharacterState()

    data class Error(var message: String) : CharacterState()

    data class CharacterListSuccess(var listOfCharacters: List<Character>) : CharacterState()
}
