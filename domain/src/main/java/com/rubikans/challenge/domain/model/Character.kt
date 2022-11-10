package com.rubikans.challenge.domain.model

data class Character(
    var id: Int = 0,
    var email: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var avatar: String = "",
) {
    val fullName: String get() = "$firstName $lastName"
}