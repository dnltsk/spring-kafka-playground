# spring-kafka-playground
have fun with spring-kafka 

## start

1. start Kafka<br>
  * `docker run -p 2181:2181 -p 9092:9092 --env ADVERTISED_HOST=`docker-machine ip \`docker-machine active\`` --env ADVERTISED_PORT=9092 spotify/kafka`
  * or `docker run -p 2181:2181 -p 9092:9092 --env ADVERTISED_HOST=localhost --env ADVERTISED_PORT=9092 spotify/kafka`

2. debug
  * create a topic: `kafka-client/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test-events`
  * list topics: `kafka-client/bin/kafka-topics.sh --list --zookeeper localhost:2181`
  * create a message: `kafka-client/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic the-test --from-beginning`<br>
    followed by {"name":"foo","message":"bar"} (return)
  * load all messages: `kafka-client/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-events --from-beginning`