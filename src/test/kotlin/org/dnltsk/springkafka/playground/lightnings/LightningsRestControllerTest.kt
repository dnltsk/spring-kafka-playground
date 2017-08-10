package org.dnltsk.springkafka.playground.lightnings

import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LightningsRestControllerTest {

    @Mock
    lateinit var repository: LightningsRepository

    @InjectMocks
    lateinit var controller: LightningsRestController

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `controller loads all lightnings`() {
        Mockito.`when`(repository.getLightnings())
                .thenReturn(listOf(LIGHTNING_2016, LIGHTNING_2017, LIGHTNING_2018))

        val lightnings = controller.getLightnings()

        verify(repository).getLightnings()
        assertThat(lightnings).hasSize(3)
    }

}