package com.rubikans.challenge.cache.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.rubikans.challenge.cache.utils.CacheConstants

@Entity(tableName = CacheConstants.CHARACTER_TABLE_NAME)
data class CharacterCache(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("first_name")
    var firstName: String? = null,
    @SerializedName("last_name")
    var lastName: String? = null,
    @SerializedName("avatar")
    var avatar: String? = null,
)