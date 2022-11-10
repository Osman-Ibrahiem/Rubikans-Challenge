package com.rubikans.challenge.data.repository

import com.rubikans.challenge.data.mapper.CharactersListMapper
import com.rubikans.challenge.data.source.CharactersCacheDataSource
import com.rubikans.challenge.data.source.CharactersRemoteDataSource
import com.rubikans.challenge.domain.model.CharactersList
import com.rubikans.challenge.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepositoryImp @Inject constructor(
    private val remoteDataSource: CharactersRemoteDataSource,
    private val cacheDataSource: CharactersCacheDataSource,
    private val charactersListMapper: CharactersListMapper,
) : CharacterRepository {

    override suspend fun getCharacters(): Flow<CharactersList> = flow {
        remoteDataSource.getCharacters().collect { entities ->
            cacheDataSource.saveCharacters(entities.characters)
            emit(charactersListMapper.mapFromEntity(entities))
        }
    }
}