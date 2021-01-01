package com.faranjit.clean.domain.spotify.interactor

import com.faranjit.clean.core.network.ResponseWrapper
import com.faranjit.clean.domain.BaseUseCase
import com.faranjit.clean.domain.spotify.model.SpotifyBaseListResponse
import com.faranjit.clean.domain.spotify.model.SpotifyTrackResponse
import com.faranjit.clean.domain.spotify.repository.SpotifyRepository
import javax.inject.Inject

/**
 * Created by Bulent Turkmen on 11.04.2020.
 */
class GetSpotifyPlaylistTracksUseCase @Inject constructor(private val spotifyRepository: SpotifyRepository) :
    BaseUseCase<SpotifyBaseListResponse<SpotifyTrackResponse>, GetSpotifyPlaylistTracksUseCase.Params>() {

    override suspend fun buildUseCase(params: Params?): ResponseWrapper<SpotifyBaseListResponse<SpotifyTrackResponse>> =
        spotifyRepository.getPlaylistTracks(params!!.playlistId, params.limit, params.offset)

    data class Params(val playlistId: String, val limit: Int = 20, val offset: Int = 0)
}