package org.dnltsk.springkafka.playground.lightnings

import com.nhaarman.mockito_kotlin.any
import org.assertj.core.api.Assertions.assertThat
import org.dnltsk.springkafka.playground.lightnings.validator.LightningValidator_3_clock
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LightningScheduledCleanupTest {

    @Mock
    lateinit var validator : LightningValidator_3_clock

    lateinit var repository : LightningsRepository

    lateinit var cleanup: LightningScheduledCleanup

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = LightningsRepository()
        cleanup = LightningScheduledCleanup(repository, validator)
        repository.addLightning(LIGHTNING_2016)
        repository.addLightning(LIGHTNING_2017)
        repository.addLightning(LIGHTNING_2018)
    }

    @Test
    fun `cleanup does not modify repository when everything is valid`() {
        assertThat(repository.getLightnings()).hasSize(3)
        `when`(validator.isOutdated(any())).thenReturn(false)
        cleanup.cleanupOutdated()
        assertThat(repository.getLightnings()).hasSize(3)
    }

    @Test
    fun `cleanup clears the whole repository when all lightnings are outdated`() {
        assertThat(repository.getLightnings()).hasSize(3)
        `when`(validator.isOutdated(any())).thenReturn(true)
        cleanup.cleanupOutdated()
        assertThat(repository.getLightnings()).hasSize(0)
    }

    @Test
    fun `cleanup clears specific lightning if it is outdated`() {
        assertThat(repository.getLightnings()).hasSize(3)
        `when`(validator.isOutdated(LIGHTNING_2016)).thenReturn(true)
        cleanup.cleanupOutdated()
        assertThat(repository.getLightnings()).hasSize(2)
        assertThat(repository.getLightnings()).doesNotContain(LIGHTNING_2016)

    }
}