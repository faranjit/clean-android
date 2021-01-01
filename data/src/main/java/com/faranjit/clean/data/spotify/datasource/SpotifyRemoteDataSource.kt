package com.faranjit.clean.data.spotify.datasource

import com.faranjit.clean.core.network.ResponseWrapper
import com.faranjit.clean.domain.Executor
import com.faranjit.clean.domain.spotify.model.SpotifyBaseListResponse
import com.faranjit.clean.domain.spotify.model.SpotifyPlaylistItem
import com.faranjit.clean.domain.spotify.model.SpotifyTrackResponse
import com.faranjit.clean.domain.spotify.remote.SpotifyApi
import javax.inject.Inject

/**
 * Created by Bulent Turkmen on 30.03.2020.
 */
class SpotifyRemoteDataSource @Inject constructor(
    private val spotifyApi: SpotifyApi,
    private val executor: Executor
) {

    suspend fun getPlaylists(
        limit: Int,
        offset: Int
    ): ResponseWrapper<SpotifyBaseListResponse<SpotifyPlaylistItem>> {
        return executor.call {
            spotifyApi.getPlaylists(limit, offset)
        }
    }

    suspend fun getPlaylistTracks(
        playlistId: String,
        limit: Int,
        offset: Int
    ): ResponseWrapper<SpotifyBaseListResponse<SpotifyTrackResponse>> = executor.call {
        spotifyApi.getPlaylistTracks(playlistId, limit, offset)
    }
}