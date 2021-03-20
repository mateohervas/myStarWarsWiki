package com.shadows.core.models.remote

import com.google.gson.annotations.SerializedName

data class RemoteFilm(
    val title: String?,
    @SerializedName("opening_crawl")
    val openingCrawl: String?,
    @SerializedName("release_date")
    val releasedDate: String?
)