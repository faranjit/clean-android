package com.faranjit.clean.core

/**
 * Created by Bulent Turkmen on 5.04.2020.
 */
open class SingleEvent<out T>(private val data: T) {

    private var hasBeenHandled = false

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            data
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = data
}