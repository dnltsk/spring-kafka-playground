package org.dnltsk.springkafka.playground

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.mockito.InjectMocks
import org.mockito.Matchers
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when`


class SomeHttpControllerTest{

    @Mock
    lateinit var someRepository : SomeRepository

    @InjectMocks
    lateinit var controller : SomeHttpController

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `controller loads foo object`() {
        val inputId = 5
        val loadedFoo = FooObject(5)
        `when`(someRepository.loadSomeFoo(any())).thenReturn(loadedFoo)

        val foo : FooObject = controller.getFoo(inputId)

        verify(someRepository).loadSomeFoo(inputId)
        assertThat(foo).isEqualTo(loadedFoo)
    }
}
