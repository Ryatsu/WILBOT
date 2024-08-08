package com.wilbot.WILBOT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wilbot.WILBOT.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
