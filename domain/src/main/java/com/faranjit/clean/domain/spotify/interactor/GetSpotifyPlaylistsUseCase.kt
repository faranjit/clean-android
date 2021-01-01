package com.faranjit.clean.domain.spotify.interactor

import com.faranjit.clean.core.network.ResponseWrapper
import com.faranjit.clean.domain.BaseUseCase
import com.faranjit.clean.domain.spotify.model.SpotifyBaseListResponse
import com.faranjit.clean.domain.spotify.model.SpotifyPlaylistItem
import com.faranjit.clean.domain.spotify.repository.SpotifyRepository
import javax.inject.Inject

/**
 * Created by Bulent Turkmen on 11.04.2020.
 */
class GetSpotifyPlaylistsUseCase @Inject constructor(private val spotifyRepository: SpotifyRepository) :
    BaseUseCase<SpotifyBaseListResponse<SpotifyPlaylistItem>, GetSpotifyPlaylistsUseCase.Params>() {

    override suspend fun buildUseCase(params: Params?): ResponseWrapper<SpotifyBaseListResponse<SpotifyPlaylistItem>> =
        spotifyRepository.getPlaylists(params!!.limit, params.offset)

    data class Params(val limit: Int = 20, val offset: Int = 0)
}