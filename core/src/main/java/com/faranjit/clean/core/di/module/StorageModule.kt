package com.faranjit.clean.core.di.module

import android.content.Context
import com.faranjit.clean.core.StorageHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Bulent Turkmen on 30.03.2020.
 */
@Module
class StorageModule {

    @Singleton
    @Provides
    fun providePreferencesHelper(context: Context): StorageHelper {
        return StorageHelper(context)
    }
}