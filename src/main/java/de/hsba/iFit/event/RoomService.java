package de.hsba.ifit.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import de.hsba.ifit.course.CourseRepository;
import de.hsba.ifit.slot.Weekday;

import javax.transaction.Transactional;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class RoomService {

    private final CourseRepository courseRepository;
    private final EventRepository eventRepository;

    public List<Room> findFreeRooms(Weekday weekday, LocalTime startTime, Integer duration) {

        int maxDuration = courseRepository.findMaxDuration();

        LocalTime timeWindowStart = startTime.minusMinutes(maxDuration);
        LocalTime timeWindowEnd = startTime.plusMinutes(duration);

        List<Event> allEventsThisDay = eventRepository.findByWeekdayOrderByStartAtAscCourseAsc(weekday);

        List<Event> allEventsInTimeslot = new ArrayList<>();

        for (Event event : allEventsThisDay) {
            if (event.getStartAt().isAfter(timeWindowStart) && event.getStartAt().isBefore(timeWindowEnd)) {
                allEventsInTimeslot.add(event);
            }
        }

        HashSet<Room> blockedRooms = new LinkedHashSet<Room>();

        for (Event event : allEventsInTimeslot) {

            if (this.checkIfEventOverlapsWithStartTime(startTime, event)) {

                blockedRooms.add(event.getRoom());
            }

        }

        List<Room> allRooms = new ArrayList<>(Arrays.asList(Room.values()));

        allRooms.removeAll(blockedRooms);

        return allRooms;
    }

    private boolean checkIfEventOverlapsWithStartTime(LocalTime startTime, Event event) {
        return startTime.isBefore(event.getStartAt().plusMinutes(event.getCourse().getDuration()));
    }

}