package com.rubikans.challenge.domain.repository

import com.rubikans.challenge.domain.model.CharactersList
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    suspend fun getCharacters(): Flow<CharactersList>
}