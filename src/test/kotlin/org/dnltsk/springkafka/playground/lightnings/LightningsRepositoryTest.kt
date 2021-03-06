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
            occurredAt = Instant.parse("2017-08-10T20:27:42Z"),
            type = LightningType.CLOUD_TO_CLOUD,
            currentInAmpere = 8000.0,
            location = Location(13.3888599, 52.5170365)
    )

    @Test
    fun `repository starts empty`() {
        val lightnings = repository.getLightnings()
        assertThat(lightnings).isEmpty()
    }

    @Test
    fun `added lightnings are part are loadable`() {
        repository.addLightning(LIGHTNING_2016)
        repository.addLightning(LIGHTNING_2017)
        repository.addLightning(LIGHTNING_2018)

        assertThat(repository.getLightnings()).hasSize(3)
    }

    @Test
    fun `added lightnings are part are removable`() {
        repository.addLightning(LIGHTNING_2016)
        repository.addLightning(LIGHTNING_2017)
        repository.addLightning(LIGHTNING_2018)

        repository.removeLightning(LIGHTNING_2017)

        assertThat(repository.getLightnings()).hasSize(2)

    }
}