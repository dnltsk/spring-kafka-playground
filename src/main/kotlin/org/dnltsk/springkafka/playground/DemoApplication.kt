package org.dnltsk.springkafka.playground

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.beans.factory.annotation.Autowired

fun main(args: Array<String>) {
    SpringApplication.run(DemoApplication::class.java, *args)
}

@SpringBootApplication
class DemoApplication @Autowired constructor(
        private val objectMapper: ObjectMapper
){

    @KafkaListener(topics = arrayOf("test-events"), containerFactory = "simpleKafkaListenerContainerFactory")
    fun eventListener(message: String) {
        val incomingEvent: IncomingEvent = objectMapper.readValue(message)
        println("incoming Event: $incomingEvent")
    }

}


