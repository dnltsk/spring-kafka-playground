package org.dnltsk.springkafka.playground.lightnings

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LightningsRestControllerIT {

    @Autowired
    lateinit var restTemplate: TestRestTemplate


    @Test
    @Suppress("UNCHECKED_CAST")
    fun findAll() {
        val response = restTemplate.getForEntity("/lightnings", List::class.java)

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body as List<Lightning>).hasSize(0)
    }

}