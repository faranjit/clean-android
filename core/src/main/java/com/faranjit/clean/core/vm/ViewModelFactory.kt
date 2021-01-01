package com.faranjit.clean.core.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by Bulent Turkmen on 28.03.2020.
 */
class ViewModelFactory @Inject constructor(private val viewModelMap: Map<Class<out ViewModel>, Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    @Throws(ClassCastException::class)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val provider: Provider<ViewModel> =
            viewModelMap[modelClass] ?: throw IllegalArgumentException("the viewModel is unknown")

        return provider.get() as T ?: throw NullPointerException("provider value is null")
    }
}