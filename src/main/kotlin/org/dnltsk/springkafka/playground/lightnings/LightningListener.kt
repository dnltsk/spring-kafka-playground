package org.dnltsk.springkafka.playground.lightnings

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import java.time.Instant

class LightningListener @Autowired constructor(
        val lightningsRepository: LightningsRepository,
        val lightningValidator_1_naive: LightningValidator_1_naive,
        val lightningValidator_2_now: LightningValidator_2_now,
        val lightningValidator_3_clock: LightningValidator_3_clock,
        val objectMapper: ObjectMapper
) {

    private val LOG = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(topics = arrayOf("test-events"), containerFactory = "simpleKafkaListenerContainerFactory")
    fun lightningListener(message: String) {
        val incomingLightning = try {
            objectMapper.readValue(message, Lightning::class.java)
        } catch (e: Throwable) {
            LOG.error("cannot deserialize lightning from $message", e)
            return
        }
        //lightningValidator_1_naive.validate(incomingLightning)
        //lightningValidator_2_now.validate(Instant.now(), incomingLightning)
        lightningValidator_3_clock.validate(incomingLightning)
        lightningsRepository.addLightning(incomingLightning)
    }

}