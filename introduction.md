# How to test time-based validations?

## Daniel Teske - @dnltsk

* GIS Engineer at MeteoGroup GmbH

## our data

### observation data

* satellite data (~every 15min)
* radar data (~every 5min)
* weather stations (~every 5min)
* road sensors (~every 1h)
* camera images (~evert 1h)
* **lightnings (~1ms)**

### forecast data

* station based (~every hour)
* numerical weather prediction models (~every 6h (up to 20GB))
* radar data

## our products

* maps, charts, tables, texts, reports
* radio-/broadcast- moderation
* consumer apps
* business "decision support"

## our daily challenges

* quality verification
* spatial context
* **temporal context** (issuedAt, validAt, validFrom, validUntil, occuredAt, contentPeriodFrom, contentPeriodUntil, ..)