package org.dnltsk.springkafka.playground.lightnings

import org.assertj.core.api.Assertions
import org.junit.Assert.*
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
        `when`(clock.instant()).thenReturn(Instant.parse("2017-01-01T12:00:00Z"))
    }

    @Test
    fun `fresh lightnings should be valid`() {
        val validLightning = LIGHTNING_2016.copy(occuredAt = Instant.parse("2017-01-01T12:00:00Z"))

        Assertions.assertThatCode {
            validator.validate(validLightning)
        }.doesNotThrowAnyException()
    }

    @Test
    fun `1h old lightnings should be valid`() {

        val validLightning = LIGHTNING_2016.copy(occuredAt = Instant.parse("2017-01-01T11:00:00Z"))

        Assertions.assertThatCode { validator.validate(validLightning) }.doesNotThrowAnyException()
    }

    @Test
    fun `4h old lightnings should be invalid`() {
        val invalidLightning = LIGHTNING_2016.copy(occuredAt = Instant.parse("2017-01-01T08:00:00Z"))

        val thrown = Assertions.catchThrowable({ validator.validate(invalidLightning) })

        Assertions.assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
        Assertions.assertThat(thrown).hasMessage("lightning is too old")

    }

}