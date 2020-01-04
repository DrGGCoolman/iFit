
package de.hsba.ifit.course;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Detailierte Kommentare befinden sich im ProductTypeRepository.java
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    Course findByName(String name);

    List<Course> findAll();

    Optional<Course> findById(Integer id);

}
