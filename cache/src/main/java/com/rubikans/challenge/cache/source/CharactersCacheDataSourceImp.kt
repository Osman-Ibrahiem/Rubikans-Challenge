package com.rubikans.challenge.cache.source

import com.rubikans.challenge.cache.dao.CharacterDao
import com.rubikans.challenge.cache.mapper.CharacterCacheMapper
import com.rubikans.challenge.data.models.CharacterEntity
import com.rubikans.challenge.data.models.CharactersListEntity
import com.rubikans.challenge.data.source.CharactersCacheDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersCacheDataSourceImp @Inject constructor(
    private val characterDao: CharacterDao,
    private val characterCacheMapper: CharacterCacheMapper,
) : CharactersCacheDataSource {

    override suspend fun getCharacters(): Flow<CharactersListEntity> = flow {
        characterDao.getCharacters().collect { cacheList ->
            val characters = cacheList.map(characterCacheMapper::mapFromCached)
            emit(CharactersListEntity(
                page = 1,
                perPage = characters.size,
                total = characters.size,
                totalPages = 1,
                characters = characters
            ))
        }
    }

    override suspend fun saveCharacters(listCharacters: List<CharacterEntity>) {
        val count =
            characterDao.addCharacters(listCharacters.map(characterCacheMapper::mapToCached))
//        characterDao.setLastCacheTime(System.currentTimeMillis())
        return count
    }
}