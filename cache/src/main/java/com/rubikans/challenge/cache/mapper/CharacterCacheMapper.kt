package com.rubikans.challenge.cache.mapper

import com.rubikans.challenge.cache.models.CharacterCache
import com.rubikans.challenge.data.models.CharacterEntity
import javax.inject.Inject

class CharacterCacheMapper @Inject constructor(
) : CacheMapper<CharacterCache, CharacterEntity> {

    override fun mapFromCached(type: CharacterCache): CharacterEntity {
        return CharacterEntity(
            id = type.id,
            email = type.email,
            firstName = type.firstName,
            lastName = type.lastName,
            avatar = type.avatar
        )
    }

    override fun mapToCached(type: CharacterEntity): CharacterCache {
        return CharacterCache(
            id = type.id,
            email = type.email,
            firstName = type.firstName,
            lastName = type.lastName,
            avatar = type.avatar
        )
    }
}