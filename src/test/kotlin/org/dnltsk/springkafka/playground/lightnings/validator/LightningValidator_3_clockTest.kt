package org.dnltsk.springkafka.playground.lightnings.validator

import org.assertj.core.api.Assertions
import org.dnltsk.springkafka.playground.lightnings.LIGHTNING_2016
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.time.Clock
import java.time.Instant

class LightningValidator_3_clockTest {

    @Mock
    lateinit var clock: Clock

    @InjectMocks
    lateinit var validator: LightningValidator_3_clock

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        `when`(clock.instant()).thenReturn(Instant.parse("2017-01-01T13:00:00Z"))
    }

    @Test
    fun `a fresh lightning should be valid`() {
        val validLightning = LIGHTNING_2016.copy(occurredAt = Instant.parse("2017-01-01T13:00:00Z"))
        Assertions.assertThatCode {
            validator.validateOccurredAt(validLightning)
        }.doesNotThrowAnyException()
    }

    @Test
    fun `a 3h old lightning should be valid`() {
        val validLightning = LIGHTNING_2016.copy(occurredAt = Instant.parse("2017-01-01T10:00:00Z"))
        Assertions.assertThatCode { validator.validateOccurredAt(validLightning) }.doesNotThrowAnyException()
    }

    @Test
    fun `a 3h 1s old lightning should be invalid`() {
        val invalidLightning = LIGHTNING_2016.copy(occurredAt = Instant.parse("2017-01-01T09:59:59Z"))
        val thrown = Assertions.catchThrowable({ validator.validateOccurredAt(invalidLightning) })
        Assertions.assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)

    }

}