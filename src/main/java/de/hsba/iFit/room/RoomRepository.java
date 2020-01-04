
package de.hsba.ifit.room;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Detailierte Kommentare befinden sich im ProductTypeRepository.java
@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    List<Room> findAll();

    Optional<Room> findById(Integer id);

}
