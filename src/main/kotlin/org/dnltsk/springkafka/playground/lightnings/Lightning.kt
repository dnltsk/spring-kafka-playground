package org.dnltsk.springkafka.playground.lightnings

import java.time.Instant

data class Lightning constructor(
        val occurredAt: Instant,
        val type: LightningType,
        val currentInAmpere: Double,
        val location: Location
)

enum class LightningType {
    CLOUD_TO_GROUND,
    GROUND_TO_CLOUD,
    CLOUD_TO_CLOUD
}

data class Location constructor(
        val lon: Double,
        val lat: Double
)