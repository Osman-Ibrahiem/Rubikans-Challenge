package com.rubikans.challenge.remote.api

import com.rubikans.challenge.remote.models.CharacterRemote
import com.rubikans.challenge.remote.models.GRemoteResponse
import com.rubikans.challenge.remote.models.PagingRemoteResponse
import retrofit2.http.*

interface CharacterService {

    @GET("users")
    suspend fun getCharacters(
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int? = null,
    ): PagingRemoteResponse<CharacterRemote>?

    @GET("users/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int,
    ): GRemoteResponse<CharacterRemote>?

    @PUT("users/{id}")
    suspend fun updateCharacter(
        @Path("id") id: Int,
        @Body request: CharacterRemote?,
    ): GRemoteResponse<CharacterRemote>?
}