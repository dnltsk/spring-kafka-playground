package org.dnltsk.springkafka.playground.lightnings.validator

import org.dnltsk.springkafka.playground.lightnings.Lightning
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.Clock
import java.time.Duration
import java.time.Instant

@Component
class LightningValidator_3_clock @Autowired constructor(
        val clock: Clock // comes from ClockConfig
) {

    private val threeHours = Duration.ofHours(3)

    fun validateOccurredAt(lightning: Lightning) {
        if (isOutdated(lightning)) {
            throw IllegalArgumentException("lightning is outdated!")
        }
    }

    fun isOutdated(lightning: Lightning): Boolean {
        val now = Instant.now(clock)
        return (lightning.occurredAt.isBefore(now.minus(threeHours)))
    }

}