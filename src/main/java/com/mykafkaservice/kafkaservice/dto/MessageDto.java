package com.mykafkaservice.kafkaservice.dto;

import lombok.Builder;

@Builder
public record MessageDto(String from, String to, String body) {

}
