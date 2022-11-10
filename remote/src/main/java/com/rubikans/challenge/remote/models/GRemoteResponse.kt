package com.rubikans.challenge.remote.models

import com.google.gson.annotations.SerializedName

open class GRemoteResponse<T> : RemoteResponse() {
    @SerializedName("data")
    var data: T? = null

    open fun hasData() = data != null
}