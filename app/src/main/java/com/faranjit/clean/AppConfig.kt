package com.faranjit.clean

import com.faranjit.clean.core.ApplicationConfig

/**
 * Created by Bulent Turkmen on 30.03.2020.
 */
object AppConfig : ApplicationConfig {

    override fun isLogEnabled(): Boolean = BuildConfig.DEBUG
}