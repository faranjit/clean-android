package com.faranjit.clean.core.ui

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Created by Bulent Turkmen on 1.04.2020.
 */
fun View.visible(visible: Boolean) {
    visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

inline fun <T> LifecycleOwner?.observeLiveData(
    liveData: LiveData<T>,
    crossinline observe: (T) -> Unit
) =
    this?.run {
        Observer<T> {
            observe(it)
        }.also {
            liveData.observe(this, it)
        }
    }