package com.rubikans.challenge.cache.source

import com.rubikans.challenge.cache.dao.CharacterDao
import com.rubikans.challenge.cache.mapper.CharacterCacheMapper
import com.rubikans.challenge.data.models.CharacterEntity
import com.rubikans.challenge.data.models.CharactersListEntity
import com.rubikans.challenge.data.source.CharactersCacheDataSource
import javax.inject.Inject

class CharactersCacheDataSourceImp @Inject constructor(
    private val characterDao: CharacterDao,
    private val characterCacheMapper: CharacterCacheMapper,
) : CharactersCacheDataSource {

    override suspend fun getCharacters(): CharactersListEntity {
        val characters = characterDao.getCharacters().map(characterCacheMapper::mapFromCached)
        return CharactersListEntity(
            page = 1,
            perPage = characters.size,
            total = characters.size,
            totalPages = 1,
            characters = characters
        )
    }

    override suspend fun saveCharacters(listCharacters: List<CharacterEntity>) {
        characterDao.addCharacters(listCharacters.map(characterCacheMapper::mapToCached))
    }

    override suspend fun saveCharacter(character: CharacterEntity) {
        characterDao.addCharacter(characterCacheMapper.mapToCached(character))
    }

    override suspend fun getCharacter(id: Int): CharacterEntity {
        return characterCacheMapper.mapFromCached(characterDao.getCharacter(id))
    }
}