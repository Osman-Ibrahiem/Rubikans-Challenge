package com.rubikans.challenge.domain.repository

import com.rubikans.challenge.domain.model.Character
import com.rubikans.challenge.domain.model.CharactersList
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    suspend fun getCharacters(page: Int): Flow<CharactersList>

    suspend fun getCharacter(id: Int): Flow<Character>

    suspend fun updateCharacter(newCharacter: Character): Flow<Character>
}