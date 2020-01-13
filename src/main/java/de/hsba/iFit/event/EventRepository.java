
package de.hsba.ifit.event;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import de.hsba.ifit.slot.Weekday;

// Detailierte Kommentare befinden sich im ProductTypeRepository.java
@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    List<Event> findAll();

    Optional<Event> findById(Integer id);

    List<Event> findByUserId(Integer id);

    List<Event> findByWeekdayOrderByStartAtAscCourseAsc(Weekday weekday);

    @Query("SELECT e FROM Event e WHERE e.startAt >= '08:00' AND e.startAt < '12:00' AND e.weekday = :#{#weekday} ORDER BY e.weekday,e.startAt ")
    List<Event> findAllMorningEventsForWeekday(@Param("weekday") Weekday weekday);

    @Query("SELECT e FROM Event e WHERE e.startAt >= '12:00' AND e.startAt < '16:00' AND e.weekday = :#{#weekday} ORDER BY e.weekday,e.startAt ")
    List<Event> findAllAfternoonEventsForWeekday(@Param("weekday") Weekday weekday);

    @Query("SELECT e FROM Event e WHERE e.startAt >= '16:00' AND e.startAt < '20:00' AND e.weekday = :#{#weekday} ORDER BY e.weekday,e.startAt ")
    List<Event> findAllEveningEventsForWeekday(@Param("weekday") Weekday weekday);

    List<Event> findByCourseId(Integer id);

    @Query("SELECT e FROM Event e WHERE e.startAt >= :#{#start} AND e.startAt < :#{#end} ")
    List<Event> findBetweenTimewindow(@Param("start") LocalTime start, @Param("end") LocalTime end);

}
