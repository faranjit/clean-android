package com.faranjit.clean.core.di

import android.app.Application
import android.content.Context
import com.faranjit.clean.core.ApplicationConfig
import com.faranjit.clean.core.Navigator
import com.faranjit.clean.core.di.module.CoreModule
import com.faranjit.clean.core.di.module.LoggerModule
import com.faranjit.clean.core.di.module.StorageModule
import dagger.BindsInstance
import dagger.Component
import timber.log.Timber
import javax.inject.Singleton

/**
 * Created by Bulent Turkmen on 28.03.2020.
 */
@Singleton
@Component(
    modules = [
        CoreModule::class,
        StorageModule::class,
        LoggerModule::class]
)
interface CoreComponent {

    fun getApplicationContext(): Context

    fun getApplicationConfig(): ApplicationConfig

    fun getLogTree(): Timber.Tree

    fun getNavigator(): Navigator

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindApplicationConfig(config: ApplicationConfig): Builder

        @BindsInstance
        fun bindNavigator(navigator: Navigator): Builder

        @BindsInstance
        fun bindApplication(application: Application): Builder

        fun build(): CoreComponent
    }
}