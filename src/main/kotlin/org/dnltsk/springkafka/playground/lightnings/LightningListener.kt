package org.dnltsk.springkafka.playground.lightnings

import com.fasterxml.jackson.databind.ObjectMapper
import org.dnltsk.springkafka.playground.lightnings.validator.LightningValidator_3_clock
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class LightningListener @Autowired constructor(
        val lightningsRepository: LightningsRepository,
        val lightningValidator: LightningValidator_3_clock,
        val objectMapper: ObjectMapper
) {

    private val LOG = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(topics = arrayOf("lightning-events"), containerFactory = "lightningKafkaListenerContainerFactory")
    fun lightningListener(message: String) {
        LOG.info("incomming lightning: $message")
        val incomingLightning = objectMapper.readValue(message, Lightning::class.java)
        lightningValidator.validateOccurredAt(incomingLightning)
        lightningsRepository.addLightning(incomingLightning)

    }

}