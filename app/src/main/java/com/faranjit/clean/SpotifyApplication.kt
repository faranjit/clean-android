package com.faranjit.clean

import com.faranjit.clean.core.di.DaggerCoreComponent
import com.faranjit.clean.di.ApplicationComponent
import com.faranjit.clean.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Bulent Turkmen on 28.03.2020.
 */
class SpotifyApplication : DaggerApplication() {

    @Inject
    lateinit var timberTree: Timber.Tree

    lateinit var applicationComponent: ApplicationComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        applicationComponent = DaggerApplicationComponent
            .factory()
            .create(
                DaggerCoreComponent
                    .builder()
                    .bindApplicationConfig(AppConfig)
                    .bindNavigator(AppNavigator)
                    .bindApplication(this)
                    .build()
            )
        return applicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        Timber.plant(timberTree)
    }
}