package com.faranjit.clean.core.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by Bulent Turkmen on 28.03.2020.
 */
@Module
class CoreModule {

    @Provides
    fun provideApplicationContext(application: Application): Context =
        application.applicationContext
}