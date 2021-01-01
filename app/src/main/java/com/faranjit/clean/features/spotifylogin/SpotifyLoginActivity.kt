package com.faranjit.clean.features.spotifylogin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.faranjit.clean.AppNavigator
import com.faranjit.clean.R
import com.faranjit.clean.SPOTIFY_CLIENT_ID
import com.faranjit.clean.SPOTIFY_REDIRECT_URI
import com.faranjit.clean.core.StorageHelper
import com.faranjit.clean.core.ui.BaseActivity
import com.faranjit.clean.databinding.ActivitySpotifyLoginBinding
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import javax.inject.Inject

class SpotifyLoginActivity : BaseActivity<SpotifyLoginViewModel, ActivitySpotifyLoginBinding>() {

    companion object {
        const val SPOTIFY_LOGIN_REQUEST_CODE = 61

        fun newIntent(context: Context) = Intent(context, SpotifyLoginActivity::class.java)
    }

    @Inject
    lateinit var viewModel: SpotifyLoginViewModel

    @Inject
    lateinit var storageHelper: StorageHelper

    override fun provideLayoutId(): Int = R.layout.activity_spotify_login

    override fun provideViewModel(): SpotifyLoginViewModel = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.viewModel = viewModel

        viewModel.spotifyTokenLiveData.observe(this, Observer {
            storageHelper.spotifyAccessToken = it
            AppNavigator.openSpotifyPlaylists(this)
            finish()
        })

        viewDataBinding.btnSpotifyGetStarted.setOnClickListener {
            if (storageHelper.spotifyAccessToken == null) {
                startSpotifyAuthentication()
            } else {
                AppNavigator.openSpotifyPlaylists(this)
                finish()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SPOTIFY_LOGIN_REQUEST_CODE) {
            viewModel.checkAuthenticationResult(resultCode, data)
        }
    }

    override fun showLoading(visible: Boolean) {
    }

    private fun startSpotifyAuthentication() {
        val authenticationRequest: AuthenticationRequest = AuthenticationRequest
            .Builder(
                SPOTIFY_CLIENT_ID, AuthenticationResponse.Type.TOKEN,
                SPOTIFY_REDIRECT_URI
            ).apply {
                setScopes(
                    arrayOf(
                        "playlist-read-private",
                        "playlist-read-collaborative"
                    )
                )
            }.build()

        AuthenticationClient.openLoginActivity(
            this,
            SPOTIFY_LOGIN_REQUEST_CODE, authenticationRequest
        )
    }
}