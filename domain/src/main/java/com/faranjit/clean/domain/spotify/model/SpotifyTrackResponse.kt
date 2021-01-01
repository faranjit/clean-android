package com.faranjit.clean.domain.spotify.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Bulent Turkmen on 6.04.2020.
 */
@JsonClass(generateAdapter = true)
data class SpotifyTrackResponse(@field:Json(name = "track") val track: SpotifyTrack)