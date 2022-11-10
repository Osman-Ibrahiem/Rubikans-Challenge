package com.rubikans.challenge.remote.source

import com.rubikans.challenge.data.models.CharactersListEntity
import com.rubikans.challenge.data.source.CharactersRemoteDataSource
import com.rubikans.challenge.remote.api.CharacterService
import com.rubikans.challenge.remote.mappers.CharactersListEntityMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersRemoteDataSourceImp @Inject constructor(
    private val characterService: CharacterService,
    private val characterListEntityMapper: CharactersListEntityMapper,
) : CharactersRemoteDataSource {

    override suspend fun getCharacters(
        page: Int,
        pageSize: Int,
    ): Flow<CharactersListEntity> = flow {
        val characters = characterService.getCharacters(
            page = page,
            perPage = pageSize,
        )
        emit(characterListEntityMapper.mapFromModel(characters))
    }
}