package com.faranjit.clean.domain.spotify.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Bulent Turkmen on 29.03.2020.
 */
@JsonClass(generateAdapter = true)
data class SpotifyPlaylistTrackItem(
    @field:Json(name = "href") val href: String,
    @field:Json(name = "total") val total: Int
)