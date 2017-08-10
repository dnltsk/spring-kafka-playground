[![Build Status](https://travis-ci.org/dnltsk/spring-kafka-playground.svg?branch=master)](https://travis-ci.org/dnltsk/spring-kafka-playground)
[![codebeat badge](https://codebeat.co/badges/555afdb7-034d-436c-84b6-014402b445eb)](https://codebeat.co/projects/github-com-dnltsk-spring-kafka-playground-master)
[![codecov](https://codecov.io/gh/dnltsk/spring-kafka-playground/branch/master/graph/badge.svg)](https://codecov.io/gh/dnltsk/spring-kafka-playground)

# spring-kafka-playground

Have fun with spring-kafka 

Application consumes time related event messages of a kafka topic

Main Features:

* Filtering of incoming outdated events
* ~~Cache warm-up on start-up~~
* ~~Cache clean up of outdated messages~~

## start

1. start Kafka<br>
  * or `docker run -p 2181:2181 -p 9092:9092 --env ADVERTISED_HOST=localhost --env ADVERTISED_PORT=9092 spotify/kafka`

2. debug
  * create a topic: `kafka-client/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test-events`
  * list topics: `kafka-client/bin/kafka-topics.sh --list --zookeeper localhost:2181`
  * create a message: `kafka-client/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic the-test --from-beginning`<br>
    followed by {"name":"foo","message":"bar"} (return)
  * load all messages: `kafka-client/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-events --from-beginning`
  
## resources

* https://github.com/eugenp/tutorials/tree/master/spring-kafka