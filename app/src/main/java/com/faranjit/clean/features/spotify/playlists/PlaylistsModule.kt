package com.faranjit.clean.features.spotify.playlists

import com.faranjit.clean.core.StorageHelper
import com.faranjit.clean.core.vm.ViewModelKey
import com.faranjit.clean.domain.spotify.interactor.GetSpotifyPlaylistsUseCase
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

/**
 * Created by Bulent Turkmen on 30.03.2020.
 */
@Module
class PlaylistsModule {

    @Provides
    @IntoMap
    @ViewModelKey(PlayListsViewModel::class)
    fun providePlaylistsViewModel(
        getSpotifyPlaylists: GetSpotifyPlaylistsUseCase,
        storageHelper: StorageHelper
    ) = PlayListsViewModel(getSpotifyPlaylists, storageHelper)
}