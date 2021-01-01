package com.faranjit.clean.core

import com.faranjit.clean.core.base.BaseUnitTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * Created by Bulent Turkmen on 18.04.2020.
 */
class SingleEventTest: BaseUnitTest() {

    @Test
    fun shouldGetContentIfNotHandled() {
        val eventData = "event data"

        val event = SingleEvent(eventData)

        assertThat(event.getContentIfNotHandled(), `is`(eventData))
    }

    @Test
    fun shouldGetNullIfHandled() {
        val eventData = "event data"

        val event = SingleEvent(eventData)
        // handle event
        event.getContentIfNotHandled()

        assertNull(event.getContentIfNotHandled())
    }

    @Test
    fun shouldGetContentEvenIfHandled() {
        val eventData = "event data"

        val event = SingleEvent(eventData)
        // handle event
        event.getContentIfNotHandled()

        assertThat(event.peekContent(), `is`(eventData))
    }
}