package org.dnltsk.springkafka.playground.lightnings

import org.dnltsk.springkafka.playground.IncomingLightning
import org.springframework.stereotype.Repository

@Repository
class LightningsRepository {

    private val lightnings = mutableListOf<Lightning>()

    fun getLightnings(): List<Lightning> {
        return lightnings
    }

    fun addLightning(incomingLightning: Lightning) {
        lightnings.add(incomingLightning)
    }

}