
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
    List<Event> findAllMorningEvents();

    @Query("SELECT e FROM Event e WHERE e.startAt >= '12:01' AND e.startAt <= '16:00' ORDER BY e.weekday,e.startAt ")
    List<Event> findAllAfternoonEvents();

    @Query("SELECT e FROM Event e WHERE e.startAt >= '16:01' AND e.startAt <= '20:00' ORDER BY e.weekday,e.startAt ")
    List<Event> findAllEveningEvents();

    // TODO: die id ziehen von getCurrentUser möglich? sollte diese query besser ins UserRepo?
    @Query("SELECT e FROM Event e WHERE e.user = 29 ORDER BY e.weekday,e.startAt ")
    List<Event> findMyEvents();

}
