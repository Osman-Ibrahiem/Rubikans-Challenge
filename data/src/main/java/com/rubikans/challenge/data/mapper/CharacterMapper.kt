package com.rubikans.challenge.data.mapper

import com.rubikans.challenge.data.mapper.Mapper
import com.rubikans.challenge.data.models.CharacterEntity
import com.rubikans.challenge.domain.model.Character
import javax.inject.Inject

class CharacterMapper @Inject constructor(
) : Mapper<CharacterEntity, Character> {

    override fun mapFromEntity(type: CharacterEntity): Character {
        return Character(
            id = type.id,
            email = type.email,
            firstName = type.firstName,
            lastName = type.lastName,
            avatar = type.avatar
        )
    }

    override fun mapToEntity(type: Character): CharacterEntity {
        return CharacterEntity(
            id = type.id,
            email = type.email,
            firstName = type.firstName,
            lastName = type.lastName,
            avatar = type.avatar
        )
    }
}