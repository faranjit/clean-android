package com.faranjit.clean.domain.spotify.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Bulent Turkmen on 29.03.2020.
 */
@JsonClass(generateAdapter = true)
data class SpotifyImage(
    @field:Json(name = "url") val url: String?,
    @field:Json(name = "width") val width: Int?,
    @field:Json(name = "height") val height: Int?
)