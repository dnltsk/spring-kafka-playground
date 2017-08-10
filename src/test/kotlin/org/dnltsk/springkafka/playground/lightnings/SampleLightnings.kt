package org.dnltsk.springkafka.playground.lightnings

import java.time.Instant

val LIGHTNING_2017 = Lightning(
        occuredAt = Instant.parse("2017-01-01T00:00:00Z"),
        type = LightningType.CLOUD_TO_CLOUD,
        currentInAmpere = 1700.0,
        location = Location(13.1956287, 52.5347694)//Berlin Spandau
)

val LIGHTNING_2016 = Lightning(
        occuredAt = Instant.parse("2016-01-01T00:00:00Z"),
        type = LightningType.CLOUD_TO_CLOUD,
        currentInAmpere = 1600.0,
        location = Location(13.3598785053087, 52.5334948)//Berlin Mitte
)

val LIGHTNING_2018 = Lightning(
        occuredAt = Instant.parse("2018-01-01T00:00:00Z"),
        type = LightningType.CLOUD_TO_CLOUD,
        currentInAmpere = 1800.0,
        location = Location(13.5764126, 52.4539099)//Berlin Koepenick
)