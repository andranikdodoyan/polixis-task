package com.mykafkaservice.kafkaservice.service;

import com.mykafkaservice.kafkaservice.dto.MessageDto;
import com.mykafkaservice.kafkaservice.entity.MessageEntity;

public interface MessageService {

  void createMessage(MessageDto messageDto);

  Iterable<MessageEntity> findAllMessages();
}
