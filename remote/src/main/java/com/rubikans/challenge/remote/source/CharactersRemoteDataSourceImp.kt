package com.rubikans.challenge.remote.source

import com.rubikans.challenge.data.models.CharacterEntity
import com.rubikans.challenge.data.models.CharactersListEntity
import com.rubikans.challenge.data.source.CharactersRemoteDataSource
import com.rubikans.challenge.remote.api.CharacterService
import com.rubikans.challenge.remote.mappers.CharacterEntityMapper
import com.rubikans.challenge.remote.mappers.CharactersListEntityMapper
import com.rubikans.challenge.remote.models.CharacterRemote
import javax.inject.Inject

class CharactersRemoteDataSourceImp @Inject constructor(
    private val characterService: CharacterService,
    private val characterListEntityMapper: CharactersListEntityMapper,
    private val characterEntityMapper: CharacterEntityMapper,
) : CharactersRemoteDataSource {

    override suspend fun getCharacters(
        page: Int,
        pageSize: Int,
    ): CharactersListEntity {
        val characters = characterService.getCharacters(
            page = page,
//            perPage = pageSize,
        )
        return characterListEntityMapper.mapFromModel(characters)
    }

    override suspend fun getCharacter(id: Int): CharacterEntity {
        val character: CharacterRemote = characterService.getCharacter(id)?.data
            ?: throw Throwable("No Character founded")
        return characterEntityMapper.mapFromModel(character)
    }

    override suspend fun updateCharacter(newCharacter: CharacterEntity): CharacterEntity {
        val character: CharacterRemote = characterService.updateCharacter(
            id = newCharacter.id,
            request = characterEntityMapper.mapToModel(newCharacter)
        )?.data ?: throw Throwable("No Character founded")
        return characterEntityMapper.mapFromModel(character)
    }
}