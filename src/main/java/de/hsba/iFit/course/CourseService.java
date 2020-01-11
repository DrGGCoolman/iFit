package de.hsba.ifit.course;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import de.hsba.ifit.user.User;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;

    public void Seed(String name, String description, Category category, TargetGroup targetGroup, int duration) {
        courseRepository.save(new Course(name, description, category, targetGroup, duration));
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public Course findCourse(Integer id) {
        return courseRepository.findById(id).orElse(null);
    }

}