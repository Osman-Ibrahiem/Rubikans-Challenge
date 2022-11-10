package com.rubikans.challenge.data.models

data class CharactersListEntity(
    var characters: List<CharacterEntity> = ArrayList(),
    var page: Int = 0,
    var perPage: Int = 0,
    var total: Int = 0,
    var totalPages: Int = 0,
)
