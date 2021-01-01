package com.faranjit.clean.core.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.runner.RunWith

/**
 * Created by Bulent Turkmen on 12.04.2020.
 */
@RunWith(AndroidJUnit4::class)
abstract class BaseInstrumentedTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

}