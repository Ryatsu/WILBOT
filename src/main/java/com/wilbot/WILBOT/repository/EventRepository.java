
package com.wilbot.WILBOT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wilbot.WILBOT.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
