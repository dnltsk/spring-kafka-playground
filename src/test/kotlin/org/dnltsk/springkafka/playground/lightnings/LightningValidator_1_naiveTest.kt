package org.dnltsk.springkafka.playground.lightnings

import org.assertj.core.api.Assertions.*
import org.dnltsk.springkafka.playground.lightnings.repository.LightningValidator_1_naive
import org.junit.Ignore
import org.junit.Test
import java.time.Duration
import java.time.Instant

class LightningValidator_1_naiveTest {

    val validator = LightningValidator_1_naive()

    @Test
    fun `fresh lightnings should be valid`() {
        val validLightning = LIGHTNING_2016.copy(occuredAt = Instant.now())

        assertThatCode { validator.validateOccuredAt(validLightning) }.doesNotThrowAnyException()
    }

    @Test
    fun `1h old lightnings should be valid`() {
        val nowMinus1Hour = Instant.now().minus(Duration.ofHours(1))
        val validLightning = LIGHTNING_2016.copy(occuredAt = nowMinus1Hour)

        assertThatCode { validator.validateOccuredAt(validLightning) }.doesNotThrowAnyException()
    }

    @Test @Ignore //ignored because we are 3h and some millis behind now :(
    fun `3h old lightnings should be valid`() {
        val nowMinus3Hour = Instant.now().minus(Duration.ofHours(3))
        val validLightning = LIGHTNING_2016.copy(occuredAt = nowMinus3Hour)

        assertThatCode { validator.validateOccuredAt(validLightning) }.doesNotThrowAnyException()
    }

    @Test
    fun `2h 59m old lightnings should be valid`() {
        val nowMinus3Hour = Instant.now().minus(Duration.ofHours(3))
        val nowMinus2h59m = nowMinus3Hour.plus(Duration.ofMinutes(1))
        val validLightning = LIGHTNING_2016.copy(occuredAt = nowMinus2h59m)

        assertThatCode { validator.validateOccuredAt(validLightning) }.doesNotThrowAnyException()
    }

    @Test
    fun `4h old lightnings should be invalid`() {
        val nowMinus4Hours = Instant.now().minus(Duration.ofHours(4))
        val invalidLightning = LIGHTNING_2016.copy(occuredAt = nowMinus4Hours)

        val thrown = catchThrowable({ validator.validateOccuredAt(invalidLightning) })

        assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
    }
}
