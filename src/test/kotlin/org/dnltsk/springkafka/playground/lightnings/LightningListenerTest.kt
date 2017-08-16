package org.dnltsk.springkafka.playground.lightnings

import com.nhaarman.mockito_kotlin.any
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.dnltsk.springkafka.playground.configuration.JacksonConfig
import org.dnltsk.springkafka.playground.lightnings.validator.LightningValidator_1_naive
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LightningListenerTest {

    @Mock
    lateinit var validator: LightningValidator_1_naive

    lateinit var repository: LightningsRepository

    lateinit var listener: LightningListener

    val validMessage = """{"occurredAt": "2017-08-14T10:59:52Z","type": "CLOUD_TO_GROUND","currentInAmpere": 12,"location": {"longitude":13, "latitude":52}}"""

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = LightningsRepository()
        listener = LightningListener(repository, validator, JacksonConfig().jsonObjectMapper())
    }

    @Test
    fun `a valid message will affect the repository`() {
        Mockito.`when`(validator.isOutdated(any())).thenReturn(false)
        listener.lightningListener(validMessage)
        assertThat(repository.getLightnings()).hasSize(1)
    }

    @Test
    fun `an outdated lightning will not affect the repository`() {
        Mockito.`when`(validator.validateOccurredAt(any())).thenThrow(IllegalArgumentException())
        val thrown = Assertions.catchThrowable { listener.lightningListener(validMessage) }
        assertThat(thrown).isInstanceOf(Throwable::class.java)
        assertThat(repository.getLightnings()).hasSize(0)
    }

    @Test
    fun `an invalid message will not affect the repository`() {
        val invalidMessage = """{"foo":"bar"}"""
        val thrown = Assertions.catchThrowable { listener.lightningListener(invalidMessage) }
        assertThat(thrown).isInstanceOf(Throwable::class.java)
        assertThat(repository.getLightnings()).hasSize(0)
    }
}