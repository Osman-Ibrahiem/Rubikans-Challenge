package com.rubikans.challenge.data.mapper

import com.rubikans.challenge.data.models.CharactersListEntity
import com.rubikans.challenge.domain.model.CharactersList
import javax.inject.Inject

class CharactersListMapper @Inject constructor(
    private var characterMapper: CharacterMapper,
) : Mapper<CharactersListEntity, CharactersList> {

    override fun mapFromEntity(type: CharactersListEntity): CharactersList {
        return CharactersList(
            page = type.page,
            perPage = type.perPage,
            total = type.total,
            totalPages = type.totalPages,
            characters = type.characters.map(characterMapper::mapFromEntity),
        )
    }

    override fun mapToEntity(type: CharactersList): CharactersListEntity {
        return CharactersListEntity(
            page = type.page,
            perPage = type.perPage,
            total = type.total,
            totalPages = type.totalPages,
            characters = type.characters.map(characterMapper::mapToEntity),
        )
    }
}