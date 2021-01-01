package com.faranjit.clean.domain.spotify.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Bulent Turkmen on 6.04.2020.
 */
@JsonClass(generateAdapter = true)
data class SpotifyArtist(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "name") val name: String
)