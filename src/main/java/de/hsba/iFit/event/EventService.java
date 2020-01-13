package de.hsba.ifit.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import de.hsba.ifit.course.Course;
import de.hsba.ifit.slot.Slot;
import de.hsba.ifit.slot.SlotService;
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
    private final SlotService slotService;

    public void Seed(LocalTime startAt, Course course, User user, Room room, Weekday weekday) {
        eventRepository.save(new Event(startAt, course, user, room, weekday));
    }

    public EventState computeEventState(Event event) {

        List<Course> userCourses = event.getUser().getCourses();

        List<Slot> userSlots = event.getUser().getSlots();

        EventState state = userCourses.contains(event.getCourse()) ? EventState.OK : EventState.CONFLICT;

        state = userSlots.contains(slotService.returnSlotForDayAndTime(event.getWeekday(), event.getStartAt())) ? state
                : EventState.CONFLICT;

        return state;
    }

    public List<Event> findAll() {

        List<Event> events = eventRepository.findAll();
        for (Event event : events) {
            event.setEventState(computeEventState(event));
        }
        return events;
    };

    public Event findById(Integer id) {

        Event event = eventRepository.findById(id).orElse(null);

        if (event != null)
            event.setEventState(computeEventState(event));

        return event;

    };

    public List<Event> findByUserId(Integer id) {
        List<Event> events = eventRepository.findByUserId(id);
        for (Event event : events) {
            event.setEventState(computeEventState(event));
        }
        return events;
    };

    public List<Event> findByWeekdayOrderByStartAtAscCourseAsc(Weekday weekday) {
        List<Event> events = eventRepository.findByWeekdayOrderByStartAtAscCourseAsc(weekday);
        for (Event event : events) {
            event.setEventState(computeEventState(event));
        }
        return events;
    };

    public List<Event> findAllMorningEventsForWeekday(Weekday weekday) {
        List<Event> events = eventRepository.findAllMorningEventsForWeekday(weekday);
        for (Event event : events) {
            event.setEventState(computeEventState(event));
        }
        return events;
    };

    public List<Event> findAllAfternoonEventsForWeekday(Weekday weekday) {
        List<Event> events = eventRepository.findAllAfternoonEventsForWeekday(weekday);
        for (Event event : events) {
            event.setEventState(computeEventState(event));
        }
        return events;
    };

    public List<Event> findAllEveningEventsForWeekday(Weekday weekday) {
        List<Event> events = eventRepository.findAllEveningEventsForWeekday(weekday);
        for (Event event : events) {
            event.setEventState(computeEventState(event));
        }
        return events;
    }

    public void delete(Event event) {
        eventRepository.delete(event);
    }

    public void save(Event event) {
        eventRepository.save(event);
    };

}