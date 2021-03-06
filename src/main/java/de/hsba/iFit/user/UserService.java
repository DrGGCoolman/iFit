package de.hsba.ifit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import de.hsba.ifit.course.Course;
import de.hsba.ifit.course.CourseRepository;
import de.hsba.ifit.event.Event;
import de.hsba.ifit.event.EventService;
import de.hsba.ifit.slot.Slot;
import de.hsba.ifit.slot.SlotService;
import de.hsba.ifit.slot.Weekday;

import javax.transaction.Transactional;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SlotService slotService;
    private final CourseRepository courseRepository;
    private final EventService eventService;

    public void Seed(String firstname, String lastname, String name, String password, String role) {
        userRepository.save(new User(firstname, lastname, name, passwordEncoder.encode(password), role));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findUsers() {
        return userRepository.findByRole(User.USER_ROLE);
    }

    public Set<User> findFittingTrainers(Weekday weekday, LocalTime time, Course selectedCourse) {

        // Hier wird aus Tag und Zeit ein Slot ausgewählt und anschließend Trainer vom
        // Repository abgerufen, welche bereit sind zu dieser Zeit , diesen Kurs zu
        // leiten.

        Slot equivalentSlot = slotService.returnSlotForDayAndTime(weekday, time);
        List<User> trainersWithFittingPreferences;
        if (equivalentSlot == null) {

            trainersWithFittingPreferences = new ArrayList<User>();

        } else {

            trainersWithFittingPreferences = userRepository.findBySlotsIdAndCoursesId(equivalentSlot.getId(),
                    selectedCourse.getId());
        }

        // Nun muss noch geprüft werden, ob die Trainer nicht bereits gebucht ist.
        List<User> availableTrainers = this.checkIfTrainersReallyHaveTime(weekday, trainersWithFittingPreferences, time,
                selectedCourse.getDuration());

        // Es wird zu einem HashSet convertiert um Duplikate zu entfernen
        return new HashSet<User>(availableTrainers);

    }

    private List<User> checkIfTrainersReallyHaveTime(Weekday weekday, List<User> fittingTrainers,
            LocalTime eventStartTime, Integer duration) {

        int maxDuration = courseRepository.findMaxDuration();
        LocalTime timeWindowStart = eventStartTime.minusMinutes(maxDuration);
        LocalTime timeWindowEnd = eventStartTime.plusMinutes(duration);

        List<Event> allEventsThisDay = eventService.findByWeekdayOrderByStartAtAscCourseAsc(weekday);

        List<Event> allEventsInTimeWindow = new ArrayList<>();

        for (Event event : allEventsThisDay) {
            if (event.getStartAt().isAfter(timeWindowStart) && event.getStartAt().isBefore(timeWindowEnd)) {
                allEventsInTimeWindow.add(event);
            }
        }

        HashSet<User> blockedTrainers = new LinkedHashSet<User>();

        for (Event event : allEventsInTimeWindow) {

            if (this.checkIfEventOverlapsWithStartTime(eventStartTime, event)) {

                blockedTrainers.add(event.getUser());
            }

        }

        fittingTrainers.removeAll(blockedTrainers);

        return fittingTrainers;

    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findUser(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    private boolean checkIfEventOverlapsWithStartTime(LocalTime startTime, Event event) {
        return startTime.isBefore(event.getStartAt().plusMinutes(event.getCourse().getDuration()));
    }
}
