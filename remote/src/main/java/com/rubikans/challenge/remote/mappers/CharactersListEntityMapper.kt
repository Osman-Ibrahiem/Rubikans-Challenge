package com.rubikans.challenge.remote.mappers

import com.rubikans.challenge.data.models.CharactersListEntity
import com.rubikans.challenge.remote.models.CharacterRemote
import com.rubikans.challenge.remote.models.PagingRemoteResponse
import javax.inject.Inject

class CharactersListEntityMapper @Inject constructor(
    private val characterMapper: CharacterEntityMapper,
) : EntityMapper<PagingRemoteResponse<CharacterRemote>, CharactersListEntity> {

    override fun mapFromModel(
        model: PagingRemoteResponse<CharacterRemote>?,
    ): CharactersListEntity {
        return CharactersListEntity(
            page = model?.page ?: 0,
            perPage = model?.perPage ?: 0,
            total = model?.total ?: 0,
            totalPages = model?.totalPages ?: 0,
            characters = model?.data?.map(characterMapper::mapFromModel) ?: ArrayList(),
        )
    }

}