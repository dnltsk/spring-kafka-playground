package org.dnltsk.springkafka.playground.lightnings.repository

import org.dnltsk.springkafka.playground.lightnings.Lightning
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.Instant

@Component
class LightningValidator_1_naive {

    private val threeHours = Duration.ofHours(3)

    fun validateOccuredAt(lightning: Lightning){
        if (isOutdated(lightning)) {
            throw IllegalArgumentException("lightning is too old")
        }
    }

    fun isOutdated(lightning: Lightning): Boolean {
        val now = Instant.now()
        return (lightning.occuredAt.isBefore(now.minus(threeHours)))
    }

}