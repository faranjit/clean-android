package com.faranjit.clean.core.vm

import androidx.lifecycle.Observer
import com.faranjit.clean.core.base.BaseUnitTest
import com.faranjit.clean.core.base.LiveDataTestUtil
import com.faranjit.clean.core.exception.UnauthorizedException
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.atLeastOnce
import org.mockito.Mockito.verify

/**
 * Created by Bulent Turkmen on 12.04.2020.
 */
class BaseViewModelTest : BaseUnitTest() {

    private lateinit var viewModel: BaseViewModel

    @Mock
    lateinit var spinnerObserver: Observer<Boolean>

    @Before
    override fun setup() {
        super.setup()

        viewModel = object : BaseViewModel() {

        }
    }

    @Test
    fun shouldDisplaySpinnerDuringRequest() {
        viewModel.spinnerLiveData.observeForever(spinnerObserver)

        viewModel.runAsync {
            // dummy lines
            val a = 2
            val b = a * a
        }

        verify(spinnerObserver).onChanged(true)
        verify(spinnerObserver, atLeastOnce()).onChanged(false)

        viewModel.spinnerLiveData.removeObserver(spinnerObserver)
    }

    @Test
    fun shouldCatchUnauthorizedLiveData() {
        viewModel.runAsync {
            throw UnauthorizedException("shouldChangeUnauthorizedLiveData")
        }

        assertThat(LiveDataTestUtil.getValue(viewModel.unauthorizedLiveData).getContentIfNotHandled()).isTrue()
    }
}