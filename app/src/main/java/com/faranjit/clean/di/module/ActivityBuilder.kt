package com.faranjit.clean.di.module

import com.faranjit.clean.features.splash.SplashActivity
import com.faranjit.clean.features.splash.SplashModule
import com.faranjit.clean.features.spotify.playlists.PlayListsActivity
import com.faranjit.clean.features.spotify.playlists.PlaylistsModule
import com.faranjit.clean.features.spotify.tracks.SpotifyTracksActivity
import com.faranjit.clean.features.spotify.tracks.SpotifyTracksModule
import com.faranjit.clean.features.spotifylogin.SpotifyLoginActivity
import com.faranjit.clean.features.spotifylogin.SpotifyLoginModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by Bulent Turkmen on 28.03.2020.
 */
@Module(includes = [AndroidSupportInjectionModule::class])
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [SplashModule::class])
    internal abstract fun bindSplash(): SplashActivity

    @ContributesAndroidInjector(modules = [SpotifyLoginModule::class])
    internal abstract fun bindSpotifyLogin(): SpotifyLoginActivity

    @ContributesAndroidInjector(modules = [PlaylistsModule::class, SpotifyModule::class])
    internal abstract fun bindSpotifyPlaylists(): PlayListsActivity

    @ContributesAndroidInjector(modules = [SpotifyTracksModule::class, SpotifyModule::class])
    internal abstract fun bindSpotifyTracks(): SpotifyTracksActivity
}