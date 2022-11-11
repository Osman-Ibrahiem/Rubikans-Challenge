package com.rubikans.challenge.data.source

import com.rubikans.challenge.data.models.CharactersListEntity

interface CharactersRemoteDataSource {

    suspend fun getCharacters(
        page: Int = 1,
        pageSize: Int = 10,
    ): CharactersListEntity
}