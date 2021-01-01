package com.faranjit.clean.domain.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.mockito.MockitoAnnotations

/**
 * Created by Bulent Turkmen on 12.04.2020.
 */
@ExperimentalCoroutinesApi
abstract class BaseUnitTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    // Set the main coroutines dispatcher for unit testing.
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    open fun setup() {
        MockitoAnnotations.initMocks(this)
    }
}