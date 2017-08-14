package org.dnltsk.springkafka.playground.lightnings

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.entry
import org.geojson.Point
import org.junit.Test

class LightningsGeoJsonConverterTest {

    val converter = LightningsGeoJsonConverter()

    @Test
    fun `empty list results in empty feature collection`() {
        val converted = converter.convert(emptyList())
        assertThat(converted).isNotNull
        assertThat(converted.features).isEmpty()
    }

    @Test
    fun `lightning is converted to a point feature`() {
        val converted = converter.convert(listOf(LIGHTNING_2016))

        assertThat(converted).isNotNull
        assertThat(converted.features).hasSize(1)

        val geometry = converted.features.get(0).geometry as Point
        assertThat(geometry.coordinates.longitude).isEqualTo(LIGHTNING_2016.location.lon)
        assertThat(geometry.coordinates.latitude).isEqualTo(LIGHTNING_2016.location.lat)
        assertThat(geometry.coordinates.altitude).isEqualTo(Double.NaN)

        val properties = converted.features.get(0).properties
        assertThat(properties).contains(entry("currentInAmpere", LIGHTNING_2016.currentInAmpere.toString()))
        assertThat(properties).contains(entry("occurredAt", LIGHTNING_2016.occurredAt.toString()))
        assertThat(properties).contains(entry("type", LIGHTNING_2016.type.toString()))
    }
}