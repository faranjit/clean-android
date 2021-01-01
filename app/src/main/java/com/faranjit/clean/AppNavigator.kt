package com.faranjit.clean

import android.content.Context
import android.os.Bundle
import com.faranjit.clean.core.Navigator
import com.faranjit.clean.features.spotify.playlists.PlayListsActivity
import com.faranjit.clean.features.spotify.tracks.SpotifyTracksActivity
import com.faranjit.clean.features.spotifylogin.SpotifyLoginActivity

/**
 * Created by Bulent Turkmen on 30.03.2020.
 */
object AppNavigator : Navigator {

    override fun openAuthorizationScreen(context: Context) =
        context.startActivity(SpotifyLoginActivity.newIntent(context))

    fun openSpotifyPlaylists(context: Context) =
        context.startActivity(PlayListsActivity.newIntent(context))

    fun openSpotifyPlaylistDetail(
        context: Context,
        playlistId: String,
        title: String,
        imageUrl: String?,
        activityOptions: Bundle?
    ) {
        SpotifyTracksActivity.newIntent(context, playlistId, title, imageUrl)
            .let {
                context.startActivity(it, activityOptions)
            }
    }
}