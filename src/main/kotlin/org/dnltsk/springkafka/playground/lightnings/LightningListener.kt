package org.dnltsk.springkafka.playground.lightnings

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.dnltsk.springkafka.playground.lightnings.validator.LightningValidator_1_naive
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.*

@Configuration
@Component
class LightningListener @Autowired constructor(
        val lightningsRepository: LightningsRepository,
        val lightningValidator: LightningValidator_1_naive,
        val objectMapper: ObjectMapper
) {

    private val LOG = LoggerFactory.getLogger(this::class.java)

    @Bean
    fun lightningKafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, String> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, String>()
        val props = HashMap<String, Any>()
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "lightning-service-" + Instant.now().toString())
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java)
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java)
        factory.consumerFactory = DefaultKafkaConsumerFactory(props)
        return factory
    }

    @KafkaListener(topics = arrayOf("lightning-events"), containerFactory = "lightningKafkaListenerContainerFactory")
    fun lightningListener(message: String) {
        LOG.info("incomming lightning: $message")
        val incomingLightning = objectMapper.readValue(message, Lightning::class.java)
        lightningValidator.validateOccurredAt(incomingLightning)
        lightningsRepository.addLightning(incomingLightning)
    }

}