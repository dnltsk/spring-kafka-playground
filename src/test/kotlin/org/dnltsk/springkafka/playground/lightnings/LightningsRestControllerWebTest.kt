package org.dnltsk.springkafka.playground.lightnings

import org.assertj.core.api.Assertions.assertThat
import org.geojson.FeatureCollection
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LightningsRestControllerWebTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun `lightnings endpoint is available`() {
        val response = restTemplate.getForEntity("/lightnings", FeatureCollection::class.java)

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }

}