# How do we test time-based validations?

## Daniel Teske - @dnltsk

* I'm a GIS Engineer at MeteoGroup GmbH
* We are using Kotlin in production since Nov 2016 (v1.0.4)

## our products

* maps, charts, tables, texts, reports
* consumer apps
* business "decision support" systems
* presentations on radio and broadcast shows

## our data

### observation data

* satellite data (~every 15min)
* radar data (~every 5min)
* weather station data (~every 5min)
* road sensor data (~every 1h)
* camera images (~evert 1h)
* **lightnings (continuously)**
* ..

### forecast data

* station based (~every hour)
* numerical weather prediction models (~every 6h)
* radar data (~every 5min)
* weather warnings (continuously)
* ..

## our daily challenges

* huge amount of data
* quality verification
* spatial context
* **temporal context**<br>
(issuedAt, validAt, validFrom, validUntil, occurredAt, contentPeriod, ..)
* ..
