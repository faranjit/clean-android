package com.faranjit.clean.domain.spotify.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Bulent Turkmen on 29.03.2020.
 */
@JsonClass(generateAdapter = true)
data class SpotifyPlaylistItem(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "description") val description: String,
    @field:Json(name = "href") val href: String,
    @field:Json(name = "uri") val uri: String,
    @field:Json(name = "images") val images: List<SpotifyImage>,
    @field:Json(name = "tracks") val tracks: SpotifyPlaylistTrackItem
)