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
class DemoApplication


