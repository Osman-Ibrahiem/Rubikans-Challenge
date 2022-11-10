package com.rubikans.challenge.remote.mappers

interface EntityMapper<M, E> {

    fun mapFromModel(model: M?): E
}