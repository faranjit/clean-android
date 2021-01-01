package com.faranjit.clean.domain.spotify.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Bulent Turkmen on 6.04.2020.
 */
@JsonClass(generateAdapter = true)
data class SpotifyTrack(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "album") val album: SpotifyAlbum,
    @field:Json(name = "artists") val artists: List<SpotifyArtist>
)