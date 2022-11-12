package com.rubikans.challenge.data.repository

import com.rubikans.challenge.data.mapper.CharacterMapper
import com.rubikans.challenge.data.mapper.CharactersListMapper
import com.rubikans.challenge.data.source.CharactersCacheDataSource
import com.rubikans.challenge.data.source.CharactersRemoteDataSource
import com.rubikans.challenge.domain.model.Character
import com.rubikans.challenge.domain.model.CharactersList
import com.rubikans.challenge.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.UnknownHostException
import javax.inject.Inject

class CharacterRepositoryImp @Inject constructor(
    private val remoteDataSource: CharactersRemoteDataSource,
    private val cacheDataSource: CharactersCacheDataSource,
    private val charactersListMapper: CharactersListMapper,
    private val characterMapper: CharacterMapper,
) : CharacterRepository {

    override suspend fun getCharacters(page: Int): Flow<CharactersList> = flow {
        try {
            remoteDataSource.getCharacters(page = page, pageSize = 10).let { entities ->
                cacheDataSource.saveCharacters(entities.characters)
                emit(charactersListMapper.mapFromEntity(entities))
            }
        } catch (throwable: Throwable) {
            if (throwable is UnknownHostException) {
                emit(charactersListMapper.mapFromEntity(cacheDataSource.getCharacters()))
            } else throw throwable
        }
    }

    override suspend fun getCharacter(id: Int): Flow<Character> = flow {
        try {
            remoteDataSource.getCharacter(id).let { character ->
                cacheDataSource.saveCharacter(character)
                emit(characterMapper.mapFromEntity(character))
            }
        } catch (throwable: Throwable) {
            if (throwable is UnknownHostException) {
                emit(characterMapper.mapFromEntity(cacheDataSource.getCharacter(id)))
            } else throw throwable
        }
    }

    override suspend fun updateCharacter(newCharacter: Character): Flow<Character> = flow {
        remoteDataSource.updateCharacter(characterMapper.mapToEntity(newCharacter))
            .let { character ->
                cacheDataSource.saveCharacter(character)
                emit(characterMapper.mapFromEntity(character))
            }
    }
}