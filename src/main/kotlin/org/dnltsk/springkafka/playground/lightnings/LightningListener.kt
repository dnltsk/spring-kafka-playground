package org.dnltsk.springkafka.playground.lightnings

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.sun.javaws.exceptions.InvalidArgumentException
import com.sun.xml.internal.ws.encoding.soap.DeserializationException
import org.dnltsk.springkafka.playground.IncomingLightning
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import java.text.ParseException

class LightningListener @Autowired constructor(
        val lightningsRepository: LightningsRepository,
        val lightningValidator: LightningValidator,
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