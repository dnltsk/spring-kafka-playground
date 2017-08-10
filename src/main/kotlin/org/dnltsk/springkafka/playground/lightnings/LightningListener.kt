package org.dnltsk.springkafka.playground.lightnings

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener

class LightningListener @Autowired constructor(
        val lightningsRepository: LightningsRepository,
        val lightningValidator: LightningValidator_1_naive,
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
        lightningValidator.validate(incomingLightning)
        lightningsRepository.addLightning(incomingLightning)
    }

}