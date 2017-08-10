package org.dnltsk.springkafka.playground.lightnings

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.Clock
import java.time.Duration
import java.time.Instant

@Component
class LightningValidator_3_clock @Autowired constructor(
        val clock: Clock
){

    private val threeHours = Duration.ofHours(3)

    fun validate(lightning: Lightning) {
        val now = Instant.now(clock)
        if (lightning.occuredAt.isBefore(now.minus(threeHours))) {
            throw IllegalArgumentException("lightning is too old")
        }
    }


}