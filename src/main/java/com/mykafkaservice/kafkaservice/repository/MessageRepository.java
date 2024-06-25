package com.mykafkaservice.kafkaservice.repository;

import com.mykafkaservice.kafkaservice.entity.MessageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<MessageEntity, String> {

}
