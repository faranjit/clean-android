package com.faranjit.clean.features.splash

import com.faranjit.clean.core.vm.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

/**
 * Created by Bulent Turkmen on 31.03.2020.
 */
@Module
class SplashModule {

    @Provides
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun provideSplashViewModel() = SplashViewModel()
}