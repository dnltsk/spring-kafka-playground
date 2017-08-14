package org.dnltsk.springkafka.playground

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.scheduling.annotation.EnableScheduling

fun main(args: Array<String>) {
    SpringApplication.run(DemoApplication::class.java, *args)
}

@SpringBootApplication
@EnableScheduling
@EnableKafka
class DemoApplication


