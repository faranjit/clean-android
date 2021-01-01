package com.faranjit.clean.domain.spotify.repository

import com.faranjit.clean.core.network.ResponseWrapper
import com.faranjit.clean.domain.spotify.model.SpotifyBaseListResponse
import com.faranjit.clean.domain.spotify.model.SpotifyPlaylistItem
import com.faranjit.clean.domain.spotify.model.SpotifyTrackResponse

/**
 * Created by Bulent Turkmen on 30.03.2020.
 */
interface SpotifyRepository {

    suspend fun getPlaylists(
        limit: Int = 20,
        offset: Int = 0
    ): ResponseWrapper<SpotifyBaseListResponse<SpotifyPlaylistItem>>

    suspend fun getPlaylistTracks(
        playlistId: String,
        limit: Int = 20,
        offset: Int = 0
    ):
            ResponseWrapper<SpotifyBaseListResponse<SpotifyTrackResponse>>
}