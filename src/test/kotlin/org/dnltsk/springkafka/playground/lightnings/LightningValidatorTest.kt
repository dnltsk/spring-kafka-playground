package org.dnltsk.springkafka.playground.lightnings

import org.dnltsk.springkafka.playground.lightnings.LightningType.CLOUD_TO_CLOUD
import org.junit.Test
import java.time.Instant
import org.assertj.core.api.Assertions.*

class LightningValidatorTest {

    val validator = LightningValidator()

    @Test
    fun `foo test`() {
        val lightning = Lightning(
                occuredAt = Instant.now(),
                type = CLOUD_TO_CLOUD,
                currentInAmpere = 8000.0,
                location = Location(13.3888599, 52.5170365)
        )
        assertThatCode { validator.validate(lightning) }.doesNotThrowAnyException()
    }
}
