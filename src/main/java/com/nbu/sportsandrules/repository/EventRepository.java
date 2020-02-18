package com.nbu.sportsandrules.repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.nbu.sportsandrules.entity.Event;

public interface EventRepository extends CrudRepository<Event, Integer> {
    Optional<Event> findById(Integer id);

    List<Event> findByName(String name);

    List<Event> findByGuestTeamId(Integer Id);

    List<Event> findByDate(OffsetDateTime date);
}
