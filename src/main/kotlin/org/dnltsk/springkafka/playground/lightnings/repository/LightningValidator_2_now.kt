package org.dnltsk.springkafka.playground.lightnings.repository

import org.dnltsk.springkafka.playground.lightnings.Lightning
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.Instant

@Component
class LightningValidator_2_now {

    private val threeHours = Duration.ofHours(3)

    fun validate(now: Instant, lightning: Lightning) {
        if (lightning.occuredAt.isBefore(now.minus(threeHours))) {
            throw IllegalArgumentException("lightning is too old")
        }
    }


}