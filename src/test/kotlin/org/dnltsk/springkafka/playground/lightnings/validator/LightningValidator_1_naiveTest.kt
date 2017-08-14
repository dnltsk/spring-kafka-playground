package org.dnltsk.springkafka.playground.lightnings.validator

import org.assertj.core.api.Assertions.*
import org.dnltsk.springkafka.playground.lightnings.LIGHTNING_2016
import org.junit.Ignore
import org.junit.Test
import java.time.Duration
import java.time.Instant

class LightningValidator_1_naiveTest {

    val validator = LightningValidator_1_naive()

    @Test
    fun `fresh lightnings should be valid`() {
        val validLightning = LIGHTNING_2016.copy(occurredAt = Instant.now())
        assertThatCode { validator.validateOccurredAt(validLightning) }.doesNotThrowAnyException()
    }

    @Test
    fun `1h old lightnings should be valid`() {
        val nowMinus1Hour = Instant.now().minus(Duration.ofHours(1))
        val validLightning = LIGHTNING_2016.copy(occurredAt = nowMinus1Hour)
        assertThatCode { validator.validateOccurredAt(validLightning) }.doesNotThrowAnyException()
    }

    @Test @Ignore //ignored because we are 3h and some millis behind now :(
    fun `3h old lightnings should be valid`() {
        val nowMinus3Hour = Instant.now().minus(Duration.ofHours(3))
        val validLightning = LIGHTNING_2016.copy(occurredAt = nowMinus3Hour)
        assertThatCode { validator.validateOccurredAt(validLightning) }.doesNotThrowAnyException()
    }

    @Test
    fun `2h 59m 59s old lightnings should be valid`() {
        val nowMinus3Hour = Instant.now().minus(Duration.ofHours(3))
        val nowMinus2h59m59s = nowMinus3Hour.plus(Duration.ofSeconds(1))
        val validLightning = LIGHTNING_2016.copy(occurredAt = nowMinus2h59m59s)
        assertThatCode { validator.validateOccurredAt(validLightning) }.doesNotThrowAnyException()
    }

    @Test
    fun `4h old lightnings should be invalid`() {
        val nowMinus4Hours = Instant.now().minus(Duration.ofHours(4))
        val invalidLightning = LIGHTNING_2016.copy(occurredAt = nowMinus4Hours)
        val thrown = catchThrowable({ validator.validateOccurredAt(invalidLightning) })
        assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
    }
}
