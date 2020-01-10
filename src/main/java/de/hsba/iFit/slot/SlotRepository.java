
package de.hsba.ifit.slot;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.hsba.ifit.daytime.Daytime;

// Detailierte Kommentare befinden sich im ProductTypeRepository.java
@Repository
public interface SlotRepository extends JpaRepository<Slot, Integer> {

    List<Slot> findAll();

    Optional<Slot> findById(Integer id);

    Optional<Slot> findByWeekdayAndDaytime(Weekday weekday, Daytime daytime);

}
