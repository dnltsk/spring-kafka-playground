package org.dnltsk.springkafka.playground.lightnings

import org.dnltsk.springkafka.playground.lightnings.LightningType.CLOUD_TO_CLOUD
import org.junit.Test
import java.time.Instant
import org.assertj.core.api.Assertions.*
import java.time.Duration

class LightningValidator_1_naiveTest {

    val validator = LightningValidator_1_naive()

    @Test
    fun `fresh lightnings should be valid`() {
        val validLightning = LIGHTNING_2016.copy(occuredAt = Instant.now())

        assertThatCode { validator.validate(validLightning) }.doesNotThrowAnyException()
    }

    @Test
    fun `1h old lightnings should be valid`() {
        val nowMinus1Hour = Instant.now().minus(Duration.ofHours(1))
        val validLightning = LIGHTNING_2016.copy(occuredAt = nowMinus1Hour)

        assertThatCode { validator.validate(validLightning) }.doesNotThrowAnyException()
    }

    @Test
    fun `4h old lightnings should be invalid`() {
        val nowMinus4Hours = Instant.now().minus(Duration.ofHours(4))
        val invalidLightning = LIGHTNING_2016.copy(occuredAt = nowMinus4Hours)

        val thrown = catchThrowable({ validator.validate(invalidLightning) })

        assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
        assertThat(thrown).hasMessage("lightning is too old")

    }
}
