package de.hsba.ifit.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import de.hsba.ifit.course.Course;
import de.hsba.ifit.user.User;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class EventService {

    private final EventRepository eventRepository;

    @PostConstruct
    void initialSeed() {
        if (eventRepository.count() == 0) {

        }
    }

    private void createEvent(LocalTime startAt, Course course, User user, Room room) {
        eventRepository.save(new Event(startAt, course, user, room));
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

}