package com.faranjit.clean.data.spotify.repository

import com.faranjit.clean.core.network.ResponseWrapper
import com.faranjit.clean.data.spotify.datasource.SpotifyRemoteDataSource
import com.faranjit.clean.domain.spotify.model.SpotifyBaseListResponse
import com.faranjit.clean.domain.spotify.model.SpotifyPlaylistItem
import com.faranjit.clean.domain.spotify.model.SpotifyTrackResponse
import com.faranjit.clean.domain.spotify.repository.SpotifyRepository

/**
 * Created by Bulent Turkmen on 30.03.2020.
 */
class SpotifyDataRepository(private val spotifyRemoteDataSource: SpotifyRemoteDataSource) :
    SpotifyRepository {

    override suspend fun getPlaylists(
        limit: Int,
        offset: Int
    ): ResponseWrapper<SpotifyBaseListResponse<SpotifyPlaylistItem>> =
        spotifyRemoteDataSource.getPlaylists(limit, offset)

    override suspend fun getPlaylistTracks(
        playlistId: String,
        limit: Int,
        offset: Int
    ): ResponseWrapper<SpotifyBaseListResponse<SpotifyTrackResponse>> =
        spotifyRemoteDataSource.getPlaylistTracks(playlistId, limit, offset)
}