package com.faranjit.clean.domain.spotify.model

import com.faranjit.clean.core.network.BaseResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Bulent Turkmen on 29.03.2020.
 */
@JsonClass(generateAdapter = true)
open class SpotifyBaseListResponse<T>(
    @field:Json(name = "items") val items: List<T>,
    @field:Json(name = "next") val next: String?
) : BaseResponse()