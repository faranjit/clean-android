package com.faranjit.clean.di

import com.faranjit.clean.SpotifyApplication
import com.faranjit.clean.core.di.CoreComponent
import com.faranjit.clean.di.module.ActivityBuilder
import com.faranjit.clean.di.module.NetworkModule
import dagger.Component
import dagger.android.AndroidInjector
import okhttp3.OkHttpClient

/**
 * Created by Bulent Turkmen on 28.03.2020.
 */
@ApplicationScope
@Component(
    modules = [ActivityBuilder::class, NetworkModule::class],
    dependencies = [CoreComponent::class]
)
interface ApplicationComponent : AndroidInjector<SpotifyApplication> {

    fun getOkHttpForGlide(): OkHttpClient

    @Component.Factory
    interface Factory {

        fun create(component: CoreComponent): ApplicationComponent
    }

}