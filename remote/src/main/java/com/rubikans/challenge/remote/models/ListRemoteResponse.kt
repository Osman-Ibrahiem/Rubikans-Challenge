package com.rubikans.challenge.remote.models

open class ListRemoteResponse<T> : GRemoteResponse<List<T?>>() {

    override fun hasData() = super.hasData() && !data.isNullOrEmpty()
}