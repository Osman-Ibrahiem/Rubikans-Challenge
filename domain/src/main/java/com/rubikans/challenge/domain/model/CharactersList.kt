package com.rubikans.challenge.domain.model

data class CharactersList(
    var characters: List<Character> = ArrayList(),
    var page: Int = 0,
    var perPage: Int = 0,
    var total: Int = 0,
    var totalPages: Int = 0,
)
