package com.mykafkaservice.kafkaservice.service;

import com.mykafkaservice.kafkaservice.dto.MessageDto;

public interface MessageService {

  void createMessage(MessageDto messageDto);
}
