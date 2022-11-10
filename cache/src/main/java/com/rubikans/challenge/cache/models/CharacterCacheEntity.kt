package com.rubikans.challenge.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.rubikans.challenge.cache.utils.CacheConstants

@Entity(tableName = CacheConstants.CHARACTER_TABLE_NAME)
data class CharacterCacheEntity(
    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("email")
    var email: String = "",
    @SerializedName("first_name")
    var firstName: String = "",
    @SerializedName("last_name")
    var lastName: String = "",
    @SerializedName("avatar")
    var avatar: String = "",
)