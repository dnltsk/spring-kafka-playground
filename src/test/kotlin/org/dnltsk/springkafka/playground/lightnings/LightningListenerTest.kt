package org.dnltsk.springkafka.playground.lightnings

import com.nhaarman.mockito_kotlin.any
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.dnltsk.springkafka.playground.configuration.JacksonConfig
import org.dnltsk.springkafka.playground.lightnings.validator.LightningValidator_3_clock
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LightningListenerTest {

    @Mock
    lateinit var validator: LightningValidator_3_clock

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
    fun `valid message will add a lightning in repository`() {
        Mockito.`when`(validator.isOutdated(any())).thenReturn(false)
        listener.lightningListener(validMessage)
        assertThat(repository.getLightnings()).hasSize(1)
    }

    @Test
    fun `invalid message will not add a lightning in repository`() {
        Mockito.`when`(validator.validateOccurredAt(any())).thenThrow(IllegalArgumentException("invalid!"))

        val thrown = Assertions.catchThrowable { listener.lightningListener(validMessage) }

        assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
        assertThat(repository.getLightnings()).hasSize(0)
    }
}