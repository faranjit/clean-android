package com.faranjit.clean.core.di.module

import com.faranjit.clean.core.ApplicationConfig
import com.faranjit.clean.core.Logger
import dagger.Module
import dagger.Provides
import timber.log.Timber

/**
 * Created by Bulent Turkmen on 28.03.2020.
 */
@Module
class LoggerModule {

    @Provides
    fun provideTimberTree(applicationConfig: ApplicationConfig): Timber.Tree =
        if (applicationConfig.isLogEnabled()) {
            Timber.DebugTree()
        } else {
            Logger()
        }
}