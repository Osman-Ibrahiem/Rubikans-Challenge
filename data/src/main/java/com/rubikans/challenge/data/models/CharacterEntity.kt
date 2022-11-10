package com.rubikans.challenge.data.models

data class CharacterEntity(
    var id: Int = 0,
    var email: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var avatar: String = "",
) {
    val fullName: String get() = "$firstName $lastName"
}