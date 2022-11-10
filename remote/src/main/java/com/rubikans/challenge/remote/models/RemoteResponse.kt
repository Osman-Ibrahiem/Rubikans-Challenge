package com.rubikans.challenge.remote.models

import com.google.gson.annotations.SerializedName

open class RemoteResponse {

    @SerializedName(value = "message", alternate = ["msg"])
    var message: String? = null

    @SerializedName("error")
    var errors: String? = null

}