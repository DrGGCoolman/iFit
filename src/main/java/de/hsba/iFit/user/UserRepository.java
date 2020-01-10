package de.hsba.ifit.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.hsba.ifit.slot.Slot;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByName(String name);

    List<User> findByRole(String role);

    List<User> findAll();

    List<User> findBySlotsId(Integer id);

    Optional<User> findById(Integer id);

}
