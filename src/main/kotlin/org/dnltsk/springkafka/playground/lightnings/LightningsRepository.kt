package org.dnltsk.springkafka.playground.lightnings

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

    fun removeLightning(lightning: Lightning){
        lightnings.remove(lightning)
    }

}