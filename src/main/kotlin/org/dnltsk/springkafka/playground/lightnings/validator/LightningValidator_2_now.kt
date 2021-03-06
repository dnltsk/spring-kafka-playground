package org.dnltsk.springkafka.playground.lightnings.validator

import org.dnltsk.springkafka.playground.lightnings.Lightning
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.Instant

@Component
class LightningValidator_2_now {

    private val threeHours = Duration.ofHours(3)

    fun validateOccurredAt(now: Instant, lightning: Lightning) {
        if (isOutdated(now, lightning)) {
            throw IllegalArgumentException("lightning is outdated!")
        }
    }

    fun isOutdated(now: Instant, lightning: Lightning): Boolean {
        return (lightning.occurredAt.isBefore(now.minus(threeHours)))
    }


}