package com.faranjit.clean.features.spotify.tracks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.faranjit.clean.core.SingleEvent
import com.faranjit.clean.core.vm.BaseViewModel
import com.faranjit.clean.domain.spotify.interactor.GetSpotifyPlaylistTracksUseCase
import com.faranjit.clean.domain.spotify.model.SpotifyBaseListResponse
import com.faranjit.clean.domain.spotify.model.SpotifyTrack
import com.faranjit.clean.domain.spotify.model.SpotifyTrackResponse
import javax.inject.Inject

/**
 * Created by Bulent Turkmen on 6.04.2020.
 */
class SpotifyTracksViewModel @Inject constructor(
    private val getSpotifyPlaylistTracks: GetSpotifyPlaylistTracksUseCase
) : BaseViewModel() {

    private var limit = 20
    private var offset = -1

    private val spotifyTracksResponse =
        MutableLiveData<SpotifyBaseListResponse<SpotifyTrackResponse>>()

    val spotifyTracks: LiveData<SingleEvent<List<SpotifyTrack>>> =
        spotifyTracksResponse.switchMap { response ->
            liveData {
                emit(
                    SingleEvent(
                        response.items.map {
                            it.track
                        }
                    )
                )
            }
        }

    val hasNext: LiveData<Boolean>
        get() = spotifyTracksResponse.switchMap { response ->
            liveData {
                emit(!response.next.isNullOrEmpty())
            }
        }

    fun getTracks(playlistId: String) {
        runAsync {
            spotifyTracksResponse.value =
                getSpotifyPlaylistTracks.execute(
                    GetSpotifyPlaylistTracksUseCase.Params(
                        playlistId,
                        limit,
                        (++offset) * limit
                    )
                )
        }
    }
}