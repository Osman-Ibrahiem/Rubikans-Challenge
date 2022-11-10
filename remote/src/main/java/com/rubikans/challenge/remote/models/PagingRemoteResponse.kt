package com.rubikans.challenge.remote.models

import com.google.gson.annotations.SerializedName

open class PagingRemoteResponse<T> : ListRemoteResponse<T>() {
    @SerializedName("page")
    var page: Int? = null

    @SerializedName("per_page")
    var perPage: Int? = null

    @SerializedName("total")
    var total: Int? = null

    @SerializedName("total_pages")
    var totalPages: Int? = null
}