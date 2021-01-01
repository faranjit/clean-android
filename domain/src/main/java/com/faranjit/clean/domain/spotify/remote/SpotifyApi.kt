package com.faranjit.clean.domain.spotify.remote

import com.faranjit.clean.domain.spotify.model.SpotifyBaseListResponse
import com.faranjit.clean.domain.spotify.model.SpotifyPlaylistItem
import com.faranjit.clean.domain.spotify.model.SpotifyTrackResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Bulent Turkmen on 29.03.2020.
 */
interface SpotifyApi {

    @GET("me/playlists")
    suspend fun getPlaylists(
        @Query("limit")
        limit: Int = 10,
        @Query("offset") offset: Int = 0
    ): SpotifyBaseListResponse<SpotifyPlaylistItem>

    @GET("playlists/{playlistId}/tracks")
    suspend fun getPlaylistTracks(
        @Path("playlistId")
        playlistId: String,
        @Query("limit")
        limit: Int = 10,
        @Query("offset")
        offset: Int = 0,
        @Query("fields")
        fields: String = "items(track(id, name, artists(id, name), album(id, name, images))),next"
    ): SpotifyBaseListResponse<SpotifyTrackResponse>
}