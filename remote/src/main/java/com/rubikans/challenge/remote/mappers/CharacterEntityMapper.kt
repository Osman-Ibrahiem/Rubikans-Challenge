package com.rubikans.challenge.remote.mappers

import com.rubikans.challenge.data.models.CharacterEntity
import com.rubikans.challenge.remote.models.CharacterRemote
import javax.inject.Inject

class CharacterEntityMapper @Inject constructor(
) : EntityMapper<CharacterRemote, CharacterEntity> {

    override fun mapFromModel(
        model: CharacterRemote?,
    ): CharacterEntity {
        return CharacterEntity(
            id = model?.id ?: 0,
            email = model?.email ?: "",
            firstName = model?.firstName ?: "",
            lastName = model?.lastName ?: "",
            avatar = model?.avatar ?: "",
        )
    }

    override fun mapToModel(entity: CharacterEntity?): CharacterRemote {
        return CharacterRemote(
            id = entity?.id,
            email = entity?.email,
            firstName = entity?.firstName,
            lastName = entity?.lastName,
            avatar = entity?.avatar,
        )
    }
}