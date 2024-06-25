package com.mykafkaservice.kafkaservice.controller;

import com.mykafkaservice.kafkaservice.dto.MessageDto;
import com.mykafkaservice.kafkaservice.entity.MessageEntity;
import com.mykafkaservice.kafkaservice.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/message")
@RequiredArgsConstructor
public class MessageController {

  private final MessageService service;

  @PostMapping("/publish")
  public void publishMessage(@RequestBody MessageDto messageDto) {
    service.createMessage(messageDto);
  }

  @GetMapping("")
  public ResponseEntity<Iterable<MessageEntity>> findAllMessages() {
    return ResponseEntity.ok(service.findAllMessages());
  }

}
