package com.mykafkaservice.kafkaservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mykafkaservice.kafkaservice.entity.MessageEntity;
import com.mykafkaservice.kafkaservice.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerService {

  private final MessageRepository messageRepository;

  @KafkaListener(topics = {"messages"}, groupId = "messages")
  public void consume(String myMsg) throws JsonProcessingException {
    log.info("Kafka Received message: {}", myMsg);

    ObjectMapper objectMapper = new ObjectMapper();
    MessageEntity message = objectMapper.readValue(myMsg, MessageEntity.class);

    message = messageRepository.save(message);
    log.info("Successfully saved message to db: {}", message);


  }
}
