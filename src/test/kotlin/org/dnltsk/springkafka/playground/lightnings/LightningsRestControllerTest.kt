package org.dnltsk.springkafka.playground.lightnings

import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LightningsRestControllerTest {

    @Mock
    lateinit var repository: LightningsRepository

    lateinit var converter: LightningsGeoJsonConverter

    lateinit var controller: LightningsRestController

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        converter = LightningsGeoJsonConverter()
        controller = LightningsRestController(repository, converter)
    }

    @Test
    fun `controller loads all lightnings`() {
        `when`(repository.getLightnings())
                .thenReturn(listOf(LIGHTNING_2016, LIGHTNING_2017, LIGHTNING_2018))

        val lightnings = controller.getLightnings()

        verify(repository).getLightnings()
        assertThat(lightnings).hasSize(3)

    }

}