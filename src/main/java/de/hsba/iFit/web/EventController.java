package de.hsba.ifit.web;

import java.util.ArrayList;
import java.util.List;

/**
 * ProductTypeController
 */

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import de.hsba.ifit.course.CourseRepository;
import de.hsba.ifit.event.*;
import de.hsba.ifit.slot.Weekday;
import de.hsba.ifit.user.User;
import de.hsba.ifit.user.UserRepository;
import de.hsba.ifit.user.UserService;
import lombok.RequiredArgsConstructor;

//Behandelt alle Anfragen bzgl. der ProductTypes. Alle routen werden unter /products/* gruppiert.
@RequiredArgsConstructor
@Controller
public class EventController {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private RoomService roomService;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    private final EventService eventService;
    private final FormAssembler formAssembler;

    // Aufruf der Event-Anlegen ansicht.
    @GetMapping("/owner/event/create")
    public String showCreateFrom(Model model) {
        model.addAttribute("eventForm", new EventForm());
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "event/event-create/step1";
    }

    // Behandelt das Anlegen eines Produktes. Validiert das Event-Anlegen
    // formular.
    @PostMapping("/owner/event/add")
    public String addEvent(@ModelAttribute("eventForm") @Valid EventForm eventForm, BindingResult result, Model model) {
        model.addAttribute("courses", courseRepository.findAll());

        if (eventForm.getWeekday() != null && eventForm.getStartAt() != null && eventForm.getCourse() != null
                && eventForm.getUser() == null && eventForm.getRoom() == null) {

            List<User> availableTrainers = userService.findFittingTrainers(eventForm.getWeekday(),
                    eventForm.getStartAt(), eventForm.getCourse());

            model.addAttribute("users", availableTrainers);

            List<Room> availableRooms = roomService.findFreeRooms(eventForm.getWeekday(), eventForm.getStartAt(),
                    eventForm.getCourse().getDuration());

            model.addAttribute("rooms", availableRooms);

            return "event/event-create/step2";
        } else if (eventForm.getWeekday() != null && eventForm.getStartAt() != null && eventForm.getCourse() != null
                && (eventForm.getUser() == null || eventForm.getRoom() == null)) {

            //FIXME: ungenutzte Variable
            List<User> availableTrainers = userService.findFittingTrainers(eventForm.getWeekday(),
                    eventForm.getStartAt(), eventForm.getCourse());
            List<Room> availableRooms = roomService.findFreeRooms(eventForm.getWeekday(), eventForm.getStartAt(),
                    eventForm.getCourse().getDuration());

            model.addAttribute("rooms", availableRooms);
            return "event/event-create/step2";
        } else if (eventForm.getWeekday() == null || eventForm.getStartAt() == null || eventForm.getCourse() == null) {
            return "event/event-create/step1";
        } else if (!result.hasErrors()) {
            eventService.save(formAssembler.update(new Event(), eventForm));
        }
        // else return "event/event-create/step1";
        // else if (eventForm.getWeekday() == null || eventForm.getStartAt() == null ||
        // eventForm.getCourse() == null) {
        // return "event/event-create/step1";
        // }

        return "redirect:/owner/event/list";
    }

    // Aufruf der Event-Beaarbeiten ansicht.
    @GetMapping("/owner/event/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Event Id:" + id));
        model.addAttribute("isUpdate", true);
        model.addAttribute("event", event);
        return "event/event-edit";
    }

    // Behandelt das Bearbeiten eines Eventes. Validiert das Event-Bearbeiten
    // formular.
    @PostMapping("/owner/event/update/{id}")
    public String updateCourse(@PathVariable("id") Integer id, @Valid Event event, BindingResult result, Model model) {
        if (result.hasErrors()) {
            event.setId(id);
            return "redirect:" + id.toString();
        }

        eventRepository.save(event);

        // return "redirect:/Event/" + id.toString();
        return "redirect:/owner/event/list";
    }

    // Behandelt das Löschen eines Eventes.
    @GetMapping("/owner/event/delete/{id}")
    public String deleteCourse(@PathVariable("id") int id, Model model) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Event Id:" + id));
        eventRepository.delete(event);
        model.addAttribute("events", eventRepository.findAll());
        return "redirect:/owner/event/list ";
    }

    // Gibt Listenansicht der Evente zurück
    @GetMapping("/owner/event/list")
    public String showAllEvents(Model model) {

        List<List<Event>> structuredEvents = new ArrayList<List<Event>>();

        for (Weekday weekday : Weekday.values()) {
            structuredEvents.add(eventRepository.findByWeekday(weekday));
        }

        model.addAttribute("eventsStruct", structuredEvents);

        return "event/event-list";
    }

    @GetMapping("/event/{id}")
    public String showEventDetails(@PathVariable("id") int id, Model model) {
        model.addAttribute("event",
                eventRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Event Id:" + id)));
        return "event/event-details";
    }
}
