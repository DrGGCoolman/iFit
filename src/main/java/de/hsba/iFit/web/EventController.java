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

import de.hsba.ifit.event.*;

//Behandelt alle Anfragen bzgl. der ProductTypes. Alle routen werden unter /products/* gruppiert.
@Controller
@RequestMapping("/owner/events/")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    // Gibt Daten zu einem bestimmten Course zurück. Benötigt dazu beim Aufruf
    // eine Id.
    // Die Daten stammen aus der Datenbank und werden über das entsprechen
    // Repository bezogen.
    @GetMapping("{id}")
    public String showCourseDetails(@PathVariable("id") Integer id, Model model) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ProductType Id:" + id));
        model.addAttribute("event", event);
        return "termin/termin-detail";
    }

    // Aufruf der Kurs-Anlegen ansicht.
    @GetMapping("create")
    public String showCreateFrom(Model model) {
        model.addAttribute("course", new Event());
        return "termin/termin-create";
    }

    // Behandelt das Anlegen eines Produktes. Validiert das Kurs-Anlegen
    // formular.
    @PostMapping("add")
    public String addCourse(@Valid Event event, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "termin/termin-create";
        }
        Event savedCourse = eventRepository.save(event);
        return "redirect:edit/" + savedCourse.getId().toString();
    }

    // Aufruf der Kurs-Beaarbeiten ansicht.
    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Event Id:" + id));
        model.addAttribute("isUpdate", true);
        model.addAttribute("event", event);
        return "termin/termin-edit";
    }

    // Behandelt das Bearbeiten eines Kurses. Validiert das Kurs-Bearbeiten
    // formular.
    @PostMapping("update/{id}")
    public String updateCourse(@PathVariable("id") Integer id, @Valid Event event, BindingResult result, Model model) {
        if (result.hasErrors()) {
            event.setId(id);
            return "redirect:" + id.toString();
        }

        eventRepository.save(event);

        // return "redirect:/kurs/" + id.toString();
        return "redirect:/owner/event/list";
    }

    // Behandelt das Löschen eines Kurses.
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") int id, Model model) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Event Id:" + id));
        eventRepository.delete(event);
        model.addAttribute("courses", eventRepository.findAll());
        return "redirect:/owner/course/list ";
    }

    // Gibt Listenansicht der Kurse zurück
    @GetMapping("list")
    public String showAllProducts(Model model) {
        model.addAttribute("courses", eventRepository.findAll());
        return "termin/termin-liste";
    }

}
