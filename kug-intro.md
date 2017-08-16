# How do we test time-based validations?

## Daniel Teske - @dnltsk

* GIS Engineer at MeteoGroup GmbH
* using Kotlin in production since Sep 2016 (v.0...)

## our products

* maps, charts, tables, texts, reports
* consumer apps
* business "decision support"
* radio-/broadcast- moderation

## our data

### observation data

* satellite data (~every 15min)
* radar data (~every 5min)
* weather station data (~every 5min)
* road sensor data (~every 1h)
* camera images (~evert 1h)
* **lightnings (~1ms)**

### forecast data

* station based (~every hour)
* numerical weather prediction models (~every 6h (up to 20GB))
* radar data (~every 5min)

## our daily challenges

* huge amount of data
* quality verification
* spatial context
* **temporal context** (issuedAt, validAt, validFrom, validUntil, occurredAt, contentPeriodFrom, contentPeriodUntil, ..)