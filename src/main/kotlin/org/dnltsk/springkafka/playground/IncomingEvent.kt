package org.dnltsk.springkafka.playground

data class IncomingEvent constructor(
        val name: String,
        val message: String
)