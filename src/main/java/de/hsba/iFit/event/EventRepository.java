
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

    @Query("SELECT e FROM Event e WHERE e.startAt <= '12:00' ")
    Collection<Event> findAllMorningEvents();

    @Query("SELECT e FROM Event e WHERE e.startAt <= '16:00' ")
    Collection<Event> findAllMiddayEvents();

    @Query("SELECT e FROM Event e WHERE e.startAt <= '20:00' ")
    Collection<Event> findAllEveningEvents();

}
