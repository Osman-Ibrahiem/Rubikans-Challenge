package com.rubikans.challenge.data.dataSource

import com.rubikans.challenge.data.models.CharactersListEntity
import kotlinx.coroutines.flow.Flow

interface CharactersRemoteDataSource {

    suspend fun getCharacters(
        page: Int = 1,
        pageSize: Int = 10,
    ): Flow<CharactersListEntity>
}