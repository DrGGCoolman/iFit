
package de.hsba.ifit.event;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// Detailierte Kommentare befinden sich im ProductTypeRepository.java
@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    List<Event> findAll();

    Optional<Event> findById(Integer id);

    @Query("SELECT e FROM Event e WHERE e.startAt >= '08:00' AND e.startAt <= '12:00' ORDER BY e.weekday,e.startAt ")
    Collection<Event> findAllMorningEvents();

    @Query("SELECT e FROM Event e WHERE e.startAt >= '12:01' AND e.startAt <= '16:00' ORDER BY e.weekday,e.startAt ")
    Collection<Event> findAllAfternoonEvents();

    @Query("SELECT e FROM Event e WHERE e.startAt >= '16:01' AND e.startAt <= '20:00' ORDER BY e.weekday,e.startAt ")
    Collection<Event> findAllEveningEvents();

    // TODO: List oder Collection
    @Query("SELECT e FROM Event e WHERE e.user = 29")
    List<Event> findMyEvents();

}
