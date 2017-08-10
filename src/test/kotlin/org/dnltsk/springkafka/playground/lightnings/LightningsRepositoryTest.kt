package org.dnltsk.springkafka.playground.lightnings

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import java.time.Instant

class LightningsRepositoryTest {

    lateinit var repository : LightningsRepository

    @Before
    fun setUp() {
        repository = LightningsRepository()
    }

    val sampleLightning = Lightning(
            occuredAt = Instant.parse("2017-08-10T20:27:42Z"),
            type = LightningType.CLOUD_TO_CLOUD,
            currentInAmpere = 8000.0,
            location = Location(13.3888599, 52.5170365)
    )

    @Test
    fun `repo starts empty`() {
        val lightnings = repository.getLightnings()
        assertThat(lightnings).isEmpty()
    }

    @Test
    fun `added lightnigs are part are loadable`() {
        repository.addLightning(sampleLightning.copy(occuredAt = Instant.parse("2016-01-01T00:00:00Z")))
        repository.addLightning(sampleLightning.copy(occuredAt = Instant.parse("2017-01-01T00:00:00Z")))
        repository.addLightning(sampleLightning.copy(occuredAt = Instant.parse("2018-01-01T00:00:00Z")))

        val lightnings = repository.getLightnings()

        assertThat(lightnings).hasSize(3)
    }

}