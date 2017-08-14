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
//        val lightningValidator_1_naive: LightningValidator_1_naive,
//        val lightningValidator_2_now: LightningValidator_2_now,
        val lightningValidator_3_clock: LightningValidator_3_clock,
        val objectMapper: ObjectMapper
) {

    private val LOG = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(topics = arrayOf("lightning-events"), containerFactory = "lightningKafkaListenerContainerFactory")
    fun lightningListener(message: String) {
        LOG.info("incomming lightning: $message")

        val incomingLightning = try {
            objectMapper.readValue(message, Lightning::class.java)
        } catch (e: Throwable) {
            LOG.error("cannot deserialize lightning from $message")
            return
        }

        try {
//            lightningValidator_1_naive.validateOccuredAt(incomingLightning)
//            lightningValidator_2_now.validateOccuredAt(Instant.now(), incomingLightning)
            lightningValidator_3_clock.validateOccuredAt(incomingLightning)
            lightningsRepository.addLightning(incomingLightning)
        } catch(e: Throwable) {
            LOG.error(e.message)
            return
        }
    }

}