package com.mykafkaservice.kafkaservice.service;

import com.mykafkaservice.kafkaservice.entity.MessageEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducerService {

  KafkaTemplate<String, MessageEntity> kafkaTemplate;

  @Autowired
  public KafkaProducerService(KafkaTemplate<String, MessageEntity> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void send(String topicName, MessageEntity value) {
    var future = kafkaTemplate.send(topicName, value);
    future.whenComplete((sendResult, exception) -> {
      if (exception != null) {
        future.completeExceptionally(exception);
      } else {
        future.complete(sendResult);
      }
      log.info("Message sent to topic \"{}\"", topicName);
    });
  }
}