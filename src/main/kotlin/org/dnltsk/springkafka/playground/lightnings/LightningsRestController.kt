package org.dnltsk.springkafka.playground.lightnings

import org.geojson.FeatureCollection
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LightningsRestController @Autowired constructor(
        val lightningsRepository: LightningsRepository,
        val lightningsGeoJsonConverter: LightningsGeoJsonConverter
) {

    @RequestMapping("/lightnings")
    fun getLightnings(): FeatureCollection {
        return lightningsGeoJsonConverter.convert(lightningsRepository.getLightnings())
    }


}