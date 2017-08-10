package org.dnltsk.springkafka.playground.lightnings

import org.assertj.core.api.Assertions.*
import org.junit.Test
import java.time.Instant

class LightningValidator_2_nowTest {

    val validator = LightningValidator_2_now()

    @Test
    fun `fresh lightnings should be valid`() {
        val validLightning = LIGHTNING_2016.copy(occuredAt = Instant.parse("2017-01-01T12:00:00Z"))

        assertThatCode {
            validator.validate(Instant.parse("2017-01-01T12:00:00Z"), validLightning)
        }.doesNotThrowAnyException()
    }

    @Test
    fun `1h old lightnings should be valid`() {

        val validLightning = LIGHTNING_2016.copy(occuredAt = Instant.parse("2017-01-01T11:00:00Z"))

        assertThatCode { validator.validate(Instant.parse("2017-01-01T12:00:00Z"), validLightning) }
                .doesNotThrowAnyException()
    }

    @Test
    fun `4h old lightnings should be invalid`() {
        val invalidLightning = LIGHTNING_2016.copy(occuredAt = Instant.parse("2017-01-01T08:00:00Z"))

        val thrown = catchThrowable({ validator.validate(Instant.parse("2017-01-01T12:00:00Z"), invalidLightning) })

        assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
        assertThat(thrown).hasMessage("lightning is too old")

    }

}