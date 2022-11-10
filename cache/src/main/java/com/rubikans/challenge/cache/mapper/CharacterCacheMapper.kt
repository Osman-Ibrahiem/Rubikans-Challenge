package com.rubikans.challenge.cache.mapper

import com.rubikans.challenge.cache.models.CharacterCacheEntity
import com.rubikans.challenge.data.models.CharacterEntity
import javax.inject.Inject

class CharacterCacheMapper @Inject constructor(
) : CacheMapper<CharacterCacheEntity, CharacterEntity> {

    override fun mapFromCached(type: CharacterCacheEntity): CharacterEntity {
        return CharacterEntity(
            id = type.id,
            email = type.email,
            firstName = type.firstName,
            lastName = type.lastName,
            avatar = type.avatar
        )
    }

    override fun mapToCached(type: CharacterEntity): CharacterCacheEntity {
        return CharacterCacheEntity(
            id = type.id,
            email = type.email,
            firstName = type.firstName,
            lastName = type.lastName,
            avatar = type.avatar
        )
    }
}