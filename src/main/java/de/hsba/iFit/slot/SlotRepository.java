
package de.hsba.ifit.slot;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.hsba.ifit.daytime.Daytime;
import de.hsba.ifit.daytime.Daytime.DaytimeName;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Integer> {

    List<Slot> findAll();

    Optional<Slot> findById(Integer id);

    List<Slot> findByDaytimeName(DaytimeName name);

    Optional<Slot> findByWeekdayAndDaytime(Weekday weekday, Daytime daytime);

    List<Slot> findByUsersId(Integer id);

}
