package com.faranjit.clean.features.spotifylogin

import androidx.lifecycle.ViewModelProvider
import com.faranjit.clean.core.vm.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

/**
 * Created by Bulent Turkmen on 28.03.2020.
 */
@Module(includes = [SpotifyLoginModule.ProvideSpotifyLoginViewModel::class])
class SpotifyLoginModule {

    @Provides
    fun provideHomeViewModel(target: SpotifyLoginActivity) =
        ViewModelProvider(target).get(SpotifyLoginViewModel::class.java)

    @Module
    class ProvideSpotifyLoginViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(SpotifyLoginViewModel::class)
        fun provideSpotifyLoginViewModel() = SpotifyLoginViewModel()
    }
}