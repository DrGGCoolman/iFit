package de.hsba.ifit.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByName(String name);

    List<User> findByRole(String role);

    List<User> findAll();

    List<User> findBySlotsIdAndCoursesId(Integer slotId, Integer courseId);

    Optional<User> findById(Integer id);

    @Query("SELECT u FROM User u WHERE u.role = 'USER' ORDER BY u.lastname, u.firstname")
    List<User> findAllTrainers();


}
