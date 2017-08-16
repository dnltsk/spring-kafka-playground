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
    fun `a fresh lightning should be valid`() {
        val validLightning = LIGHTNING_2016.copy(occurredAt = Instant.now())
        assertThatCode { validator.validateOccurredAt(validLightning) }.doesNotThrowAnyException()
    }

    @Test @Ignore //ignored because we are 3h and _some millis_ behind _now_ :(
    fun `a 3h old lightning should be valid`() {
        val nowMinus3Hour = Instant.now().minus(Duration.ofHours(3))
        val validLightning = LIGHTNING_2016.copy(occurredAt = nowMinus3Hour)
        assertThatCode { validator.validateOccurredAt(validLightning) }.doesNotThrowAnyException()
    }

    @Test
    fun `a 2h 59m 59s old lightning should be valid`() {
        val nowMinus2h59m59s = Instant.now()
                .minus(Duration.ofHours(2))
                .minus(Duration.ofMinutes(59))
                .minusSeconds(59)
        val validLightning = LIGHTNING_2016.copy(occurredAt = nowMinus2h59m59s)
        assertThatCode { validator.validateOccurredAt(validLightning) }.doesNotThrowAnyException()
    }

    @Test
    fun `a 3h 1s old lightning should be invalid`() {
        val nowMinus3h1s = Instant.now()
                .minus(Duration.ofHours(3))
                .minusSeconds(1)
        val invalidLightning = LIGHTNING_2016.copy(occurredAt = nowMinus3h1s)
        val thrown = catchThrowable({ validator.validateOccurredAt(invalidLightning) })
        assertThat(thrown).isInstanceOf(Throwable::class.java)
    }

}
