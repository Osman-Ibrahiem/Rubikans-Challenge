package com.rubikans.challenge.data.source

import com.rubikans.challenge.data.models.CharacterEntity
import com.rubikans.challenge.data.models.CharactersListEntity

interface CharactersCacheDataSource {

    suspend fun getCharacters(): CharactersListEntity

    suspend fun saveCharacters(listCharacters: List<CharacterEntity>)

    suspend fun saveCharacter(character: CharacterEntity)

    suspend fun getCharacter(id: Int): CharacterEntity

}