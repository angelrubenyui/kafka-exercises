package org.ogomez.nontx;

import static java.util.Collections.singleton;
import static org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.ISOLATION_LEVEL_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.TRANSACTIONAL_ID_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;

import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

public class KafkaConfig {


  static final String BOOTSTRAP_SERVERS = "127.0.0.1:9092, 127.0.0.1:9093, 127.0.0.1:9094";
  static final String  CONSUMER_GROUP_ID = "simple-group";
  static final String INPUT_TOPIC = "simple-topic";
  public static final String SERIALIZATION_STRING_DESERIALIZER = "org.apache.kafka.common.serialization.StringDeserializer";
  public static final String SERIALIZATION_STRING_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";

  static KafkaConsumer<String, String> createKafkaConsumer() {
    Properties props = new Properties();
    props.put(BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
    props.put(GROUP_ID_CONFIG, CONSUMER_GROUP_ID);
    props.put(AUTO_OFFSET_RESET_CONFIG, "earliest");
    props.put(KEY_DESERIALIZER_CLASS_CONFIG,
        SERIALIZATION_STRING_DESERIALIZER);
    props.put(VALUE_DESERIALIZER_CLASS_CONFIG,
        SERIALIZATION_STRING_DESERIALIZER);

    KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
    consumer.subscribe(singleton(INPUT_TOPIC));
    return consumer;
  }

  static KafkaProducer<String, String> createKafkaProducer() {

    Properties props = new Properties();
    props.put(BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
    props
        .put(KEY_SERIALIZER_CLASS_CONFIG, SERIALIZATION_STRING_SERIALIZER);
    props.put(VALUE_SERIALIZER_CLASS_CONFIG,
        SERIALIZATION_STRING_SERIALIZER);

    return new KafkaProducer<>(props);

  }

}