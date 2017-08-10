package org.dnltsk.springkafka.playground.lightnings

import java.time.Instant

data class Lightning constructor(
        val occuredAt: Instant,
        val type: LightningType,
        val currentInAmpere: Double,
        val location: Location
)