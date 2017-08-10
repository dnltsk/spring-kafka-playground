package org.dnltsk.springkafka.playground.lightnings

import org.springframework.stereotype.Component
import java.time.Duration
import java.time.Instant

@Component
class LightningValidator_1_naive {

    private val threeHours = Duration.ofHours(3)

    fun validate(lightning: Lightning){
        val now = Instant.now()
        if(lightning.occuredAt.isBefore(now.minus(threeHours))){
            throw IllegalArgumentException("lightning is too old")
        }
    }


}