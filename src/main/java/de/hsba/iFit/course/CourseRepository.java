
package de.hsba.ifit.course;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    Course findByName(String name);

    List<Course> findAll();

    Optional<Course> findById(Integer id);

    @Query("SELECT MAX(duration) FROM Course")
    Integer findMaxDuration();

    @Query("SELECT c FROM Course c ORDER BY c.name")
    List<Course> findAllCourses();

    List<Course> findByUsersId(Integer id);

}
