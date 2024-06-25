package com.mykafkaservice.kafkaservice.config;

import com.mykafkaservice.kafkaservice.entity.MessageEntity;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
@EnableKafka
public class KafkaConfig {

  @Value("${spring.kafka.bootstrap-servers}")
  String bootstrapServers;
  @Bean
  public ProducerFactory<String, MessageEntity> producerFactory() {
    Map<String, Object> config = new HashMap<>();
    config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
    config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
    return new DefaultKafkaProducerFactory<>(config);
  }
  @Bean
  public NewTopic taskTopic() {
    return TopicBuilder.name("messages")
        .partitions(1)
        .replicas(1)
        .build();
  }
  @Bean
  public KafkaTemplate<String, MessageEntity> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

}
