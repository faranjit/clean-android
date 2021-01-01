package com.faranjit.clean.features.spotify.tracks

import com.faranjit.clean.core.vm.ViewModelKey
import com.faranjit.clean.domain.spotify.interactor.GetSpotifyPlaylistTracksUseCase
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

/**
 * Created by Bulent Turkmen on 6.04.2020.
 */
@Module
class SpotifyTracksModule {

    @Provides
    @IntoMap
    @ViewModelKey(SpotifyTracksViewModel::class)
    fun provideSpotifyTracksViewModel(
        getSpotifyPlaylistTracks: GetSpotifyPlaylistTracksUseCase
    ) = SpotifyTracksViewModel(getSpotifyPlaylistTracks)
}