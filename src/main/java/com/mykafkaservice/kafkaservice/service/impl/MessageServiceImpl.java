package com.mykafkaservice.kafkaservice.service.impl;

import com.mykafkaservice.kafkaservice.dto.MessageDto;
import com.mykafkaservice.kafkaservice.entity.MessageEntity;
import com.mykafkaservice.kafkaservice.repository.MessageRepository;
import com.mykafkaservice.kafkaservice.service.KafkaProducerService;
import com.mykafkaservice.kafkaservice.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

  private final MessageRepository repository;
  private final KafkaProducerService kafkaProducerService;


  public void createMessage(MessageDto messageDto) {
    if (messageDto != null && messageDto.from() != null && messageDto.to() != null) {
      MessageEntity message = new MessageEntity();
      message.setBody(messageDto.body());
      message.setFromUser(messageDto.from());
      message.setToUser(messageDto.to());
      log.info("Creating new message from {} to {}", messageDto.from(), messageDto.to());
      log.info("Publishing new message from {} to {} to Kafka", messageDto.from(), messageDto.to());

      kafkaProducerService.send("messages", message);

    } else {
      log.info("Failed to create new message");
    }

  }

}
