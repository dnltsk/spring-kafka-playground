package org.dnltsk.springkafka.playground.lightnings.validator

import org.assertj.core.api.Assertions.*
import org.dnltsk.springkafka.playground.lightnings.LIGHTNING_2016
import org.junit.Test
import java.time.Instant

class LightningValidator_2_nowTest {

    val validator = LightningValidator_2_now()

    val TEST_NOW = Instant.parse("2017-01-01T13:00:00Z")

    @Test
    fun `a fresh lightnings should be valid`() {
        val validLightning = LIGHTNING_2016.copy(occurredAt = TEST_NOW)
        assertThatCode {
            validator.validateOccurredAt(TEST_NOW, validLightning)
        }.doesNotThrowAnyException()
    }

    @Test
    fun `a 3h old lightnings should be valid`() {
        val validLightning = LIGHTNING_2016.copy(occurredAt = Instant.parse("2017-01-01T10:00:00Z"))
        assertThatCode { validator.validateOccurredAt(TEST_NOW, validLightning) }
                .doesNotThrowAnyException()
    }

    @Test
    fun `a 3h 1s old lightnings should be invalid`() {
        val outdatedLightning = LIGHTNING_2016.copy(occurredAt = Instant.parse("2017-01-01T09:59:59Z"))
        val thrown = catchThrowable({ validator.validateOccurredAt(TEST_NOW, outdatedLightning) })
        assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
    }

}