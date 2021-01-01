package com.faranjit.clean.features.splash

import android.os.Bundle
import android.os.Handler
import com.faranjit.clean.AppNavigator
import com.faranjit.clean.R
import com.faranjit.clean.core.StorageHelper
import com.faranjit.clean.core.ui.BaseActivity
import com.faranjit.clean.databinding.ActivitySplashBinding
import javax.inject.Inject

/**
 * Created by Bulent Turkmen on 31.03.2020.
 */
class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>() {

    @Inject
    lateinit var viewModel: SplashViewModel

    @Inject
    lateinit var storageHelper: StorageHelper

    override fun provideLayoutId(): Int = R.layout.activity_splash

    override fun provideViewModel(): SplashViewModel = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed(Runnable {
            if (storageHelper.spotifyAccessToken == null) {
                AppNavigator.openAuthorizationScreen(this)
                finish()
            } else {
                AppNavigator.openSpotifyPlaylists(this)
                finish()
            }
        }, 1500)
    }

    override fun showLoading(visible: Boolean) {
    }
}