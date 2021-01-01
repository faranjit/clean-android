package com.faranjit.clean.features.spotify.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.faranjit.clean.core.SingleEvent
import com.faranjit.clean.core.StorageHelper
import com.faranjit.clean.core.vm.BaseViewModel
import com.faranjit.clean.domain.spotify.interactor.GetSpotifyPlaylistsUseCase
import com.faranjit.clean.domain.spotify.model.SpotifyBaseListResponse
import com.faranjit.clean.domain.spotify.model.SpotifyPlaylistItem
import javax.inject.Inject

/**
 * Created by Bulent Turkmen on 30.03.2020.
 */
class PlayListsViewModel @Inject constructor(
    private val getSpotifyPlaylists: GetSpotifyPlaylistsUseCase,
    private val storageHelper: StorageHelper
) :
    BaseViewModel() {

    private var limit = 20
    private var offset = -1

    private val spotifyPlaylistsResponse =
        MutableLiveData<SpotifyBaseListResponse<SpotifyPlaylistItem>>()

    val spotifyPlaylists: LiveData<SingleEvent<List<SpotifyPlaylistItem>>> =
        spotifyPlaylistsResponse.switchMap { response ->
            liveData {
                emit(SingleEvent(response.items))
            }
        }

    val hasNext: LiveData<Boolean>
        get() = spotifyPlaylistsResponse.switchMap { response ->
            liveData {
                emit(!response.next.isNullOrEmpty())
            }
        }

    init {
        fetchNextPlaylists()
    }

    fun fetchNextPlaylists() {
        runAsync {
            spotifyPlaylistsResponse.value =
                getSpotifyPlaylists.execute(
                    GetSpotifyPlaylistsUseCase.Params(
                        limit,
                        (++offset) * limit
                    )
                )
        }
    }

    fun clearSpotifyAccessToken() {
        storageHelper.spotifyAccessToken = null
    }
}