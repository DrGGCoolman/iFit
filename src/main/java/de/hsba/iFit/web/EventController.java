package de.hsba.ifit.web;

/**
 * ProductTypeController
 */

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import de.hsba.ifit.course.CourseRepository;
import de.hsba.ifit.event.*;
import de.hsba.ifit.user.UserRepository;
import de.hsba.ifit.user.UserService;

//Behandelt alle Anfragen bzgl. der ProductTypes. Alle routen werden unter /products/* gruppiert.
@Controller
@RequestMapping("/owner/event/")
public class EventController {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    // Aufruf der Event-Anlegen ansicht.
    @GetMapping("create")
    public String showCreateFrom(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "event/event-create/step1";
    }

    // Behandelt das Anlegen eines Produktes. Validiert das Event-Anlegen
    // formular.
    @PostMapping("add")
    public String addEvent(@Valid Event event, BindingResult result, Model model) {
        model.addAttribute("courses", courseRepository.findAll());

        if (event.getWeekday() != null && event.getStartAt() != null && event.getUser() == null
                && event.getRoom() == null) {

            model.addAttribute("users",
                    userService.findFittingTrainer(event.getWeekday(), event.getStartAt(), event.getCourse()));
            return "event/event-create/step2";


            
        } else if (!result.hasErrors()) {
            eventRepository.save(event);
        }
        // if (result.hasErrors()) {
        // return "termin/termin-create";
        // }

        return "redirect:/owner/event/list";
    }

    // Aufruf der Event-Beaarbeiten ansicht.
    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Event Id:" + id));
        model.addAttribute("isUpdate", true);
        model.addAttribute("event", event);
        return "event/event-edit";
    }

    // Behandelt das Bearbeiten eines Eventes. Validiert das Event-Bearbeiten
    // formular.
    @PostMapping("update/{id}")
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
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") int id, Model model) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Event Id:" + id));
        eventRepository.delete(event);
        model.addAttribute("events", eventRepository.findAll());
        return "redirect:/owner/event/list ";
    }

    // Gibt Listenansicht der Evente zurück
    @GetMapping("list")
    public String showAllProducts(Model model) {
        model.addAttribute("events", eventRepository.findAll());
        return "event/event-list";
    }

    @GetMapping("{id}")
    public String showEventDetails(@PathVariable("id") int id, Model model) {
        model.addAttribute("events", eventRepository.findById(id));
        return "event/event-details";
    }
}
