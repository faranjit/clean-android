package com.faranjit.clean.core

import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.faranjit.clean.core.base.BaseInstrumentedTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * Created by Bulent Turkmen on 12.04.2020.
 */
class StorageHelperTest : BaseInstrumentedTest() {

    companion object {
        const val KEY = "key"
        const val VALUE = "value"
    }

    private val storageHelper = StorageHelper(getApplicationContext())

    @Test
    fun shouldPutString() {
        storageHelper.putString(KEY, VALUE)

        assertThat(storageHelper.getString(KEY), `is`(VALUE))
    }
}