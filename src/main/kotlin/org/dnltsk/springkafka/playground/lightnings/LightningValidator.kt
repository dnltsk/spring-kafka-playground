package org.dnltsk.springkafka.playground.lightnings

import org.springframework.stereotype.Component
import java.time.Duration
import java.time.Instant

@Component
class LightningValidator{

    private val threeHours = Duration.ofHours(3)

    fun validate(lightning: Lightning) {
        val now = Instant.now()
        if(now.isBefore(lightning.occuredAt.minus(threeHours))){
            throw IllegalArgumentException("lightning is too old")
        }
    }


}