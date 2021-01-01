package com.faranjit.clean.core.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faranjit.clean.core.SingleEvent
import com.faranjit.clean.core.exception.UnauthorizedException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by Bulent Turkmen on 28.03.2020.
 */
abstract class BaseViewModel : ViewModel() {

    private val _spinnerLiveData = MutableLiveData<Boolean>(false)
    /**
     * Display loading indicator when requested
     */
    val spinnerLiveData: LiveData<Boolean>
        get() = _spinnerLiveData

    var unauthorizedLiveData = MutableLiveData<SingleEvent<Boolean>>()

    /**
     * Call async function such as api requests with a loading indicator
     *
     * @param block lambda function to run long running tasks.
     *              Before it runs the loading indicator will display
     *              and after it finished the indicator will dismiss.
     */
    fun runAsync(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            try {
                _spinnerLiveData.value = true

                block.invoke(this)
            } catch (t: UnauthorizedException) {
                Timber.e(t)

                unauthorizedLiveData.value = SingleEvent(true)
            } catch (t: Throwable) {
                Timber.e(t)
            } finally {
                _spinnerLiveData.value = false
            }
        }
    }

}