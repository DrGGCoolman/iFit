package de.hsba.ifit.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import de.hsba.ifit.course.Course;
import de.hsba.ifit.course.CourseRepository;
import de.hsba.ifit.slot.Weekday;
import de.hsba.ifit.user.User;

import javax.transaction.Transactional;

import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class EventService {

    private final EventRepository eventRepository;
    private final CourseRepository courseRepository;

    public void Seed(LocalTime startAt, Course course, User user, Room room, Weekday weekday) {
        eventRepository.save(new Event(startAt, course, user, room, weekday));
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

}