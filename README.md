[![Build Status](https://travis-ci.org/dnltsk/spring-kafka-playground.svg?branch=master)](https://travis-ci.org/dnltsk/spring-kafka-playground)
[![codebeat badge](https://codebeat.co/badges/555afdb7-034d-436c-84b6-014402b445eb)](https://codebeat.co/projects/github-com-dnltsk-spring-kafka-playground-master)
[![codecov](https://codecov.io/gh/dnltsk/spring-kafka-playground/branch/master/graph/badge.svg)](https://codecov.io/gh/dnltsk/spring-kafka-playground)

# spring-kafka-playground - Have fun with spring-kafka 

Application consumes time related event messages from a kafka topic

![Diagram](/src/main/resources/META-INF/spring-kafka-playground.png)

Main Features:

* Filtering of incoming outdated events
* Cache warm-up on start-up
* Cache clean up of outdated messages

## build

* `gradle build`

## start 

* `java -jar build/libs/spring-kafka-playground-*.jar`
* access http://localhost:8080/lightnings

## start kafka

* `docker run -p 2181:2181 -p 9092:9092 --env ADVERTISED_HOST=localhost --env ADVERTISED_PORT=9092 spotify/kafka`

## create lightning events


* create a topic:<br> 
  `kafka-client/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic lightning-events`
* list topics:<br>
  `kafka-client/bin/kafka-topics.sh --list --zookeeper localhost:2181`
* create a message:<br>
  `kafka-client/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic lightning-events`<br>
  followed by<br>
  `{"occurredAt": "2017-08-15T07:16:55Z","type": "CLOUD_TO_GROUND","currentInAmpere": 12,"location": {"lon":12, "lat":53}}` (return)<br>
  **don't forget to change occurredAt to "now"!**
* load all messages:<br>
  `kafka-client/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic lightning-events --from-beginning`
  
## resources

* https://github.com/eugenp/tutorials/tree/master/spring-kafka