package org.dnltsk.springkafka.playground.lightnings

import org.dnltsk.springkafka.playground.lightnings.validator.LightningValidator_3_clock
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class LightningScheduledCleanup @Autowired constructor(
        val lightningRepository: LightningsRepository,
        val lightningValidator: LightningValidator_3_clock
) {
    val LOG = LoggerFactory.getLogger(this::class.java)

    @Scheduled(fixedRate = 10_000, initialDelay = 10_000)
    fun cleanup() {
        lightningRepository
                .getLightnings()
                .filter(lightningValidator::isOutdated)
                .onEach { LOG.info("removing lightning ${it}") }
                .forEach(lightningRepository::removeLightning)
    }

}