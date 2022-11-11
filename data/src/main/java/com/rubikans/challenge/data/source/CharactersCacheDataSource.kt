package com.rubikans.challenge.data.source

import com.rubikans.challenge.data.models.CharacterEntity
import com.rubikans.challenge.data.models.CharactersListEntity
import kotlinx.coroutines.flow.Flow

interface CharactersCacheDataSource {

    suspend fun getCharacters(): CharactersListEntity

    suspend fun saveCharacters(listCharacters: List<CharacterEntity>)

}