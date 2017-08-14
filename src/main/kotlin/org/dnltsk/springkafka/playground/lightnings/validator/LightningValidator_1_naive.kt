package org.dnltsk.springkafka.playground.lightnings.validator

import org.dnltsk.springkafka.playground.lightnings.Lightning
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.Instant

@Component
class LightningValidator_1_naive {

    private val threeHours = Duration.ofHours(3)

    fun validateOccurredAt(lightning: Lightning){
        if (isOutdated(lightning)) {
            throw IllegalArgumentException("lightning is outdated!")
        }
    }

    fun isOutdated(lightning: Lightning): Boolean {
        val now = Instant.now()
        return (lightning.occurredAt.isBefore(now.minus(threeHours)))
    }

}