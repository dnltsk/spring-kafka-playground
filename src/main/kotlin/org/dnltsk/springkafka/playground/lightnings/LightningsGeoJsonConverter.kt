package org.dnltsk.springkafka.playground.lightnings

import org.geojson.Feature
import org.geojson.FeatureCollection
import org.geojson.Point
import org.springframework.stereotype.Component

@Component
class LightningsGeoJsonConverter {

    fun convert(lightnings: List<Lightning>): FeatureCollection {
        val fc = FeatureCollection()
        fc.features = lightnings.map {
            val feature = Feature()
            feature.geometry = Point(it.location.lon, it.location.lat)
            feature.properties = mapOf(
                    Pair("currentInAmpere", it.currentInAmpere.toString()),
                    Pair("occurredAt", it.occurredAt.toString()),
                    Pair("type", it.type.toString())
            )
            feature
        }
        return fc
    }

}