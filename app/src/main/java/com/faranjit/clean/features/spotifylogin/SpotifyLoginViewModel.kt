package com.faranjit.clean.features.spotifylogin

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.faranjit.clean.core.vm.BaseViewModel
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationResponse
import timber.log.Timber

/**
 * Created by Bulent Turkmen on 28.03.2020.
 */
class SpotifyLoginViewModel : BaseViewModel() {

    private val spotifyToken = MutableLiveData<String>()
    val spotifyTokenLiveData: LiveData<String>
        get() = spotifyToken

    fun checkAuthenticationResult(resultCode: Int, data: Intent?) {
        val authenticationResponse: AuthenticationResponse =
            AuthenticationClient.getResponse(resultCode, data)

        when (authenticationResponse.type) {
            AuthenticationResponse.Type.TOKEN -> spotifyToken.value =
                authenticationResponse.accessToken
            AuthenticationResponse.Type.ERROR -> Timber.e(authenticationResponse.error)
            else -> Timber.e("could not login spotify")
        }
    }
}