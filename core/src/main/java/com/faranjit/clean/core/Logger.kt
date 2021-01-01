package com.faranjit.clean.core

import timber.log.Timber

/**
 * Created by Bulent Turkmen on 28.03.2020.
 */
class Logger : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String?, t: Throwable?) {
        TODO("implement logging logic")
    }
}