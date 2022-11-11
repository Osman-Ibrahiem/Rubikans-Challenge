package com.rubikans.challenge.domain.repository

import com.rubikans.challenge.domain.model.CharactersList
import com.rubikans.challenge.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    suspend fun getCharacters(page:Int): Flow<CharactersList>

    suspend fun getCharacter(id: Int): Flow<Character>
}