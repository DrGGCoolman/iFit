package de.hsba.ifit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import de.hsba.ifit.course.CourseRepository;
import de.hsba.ifit.event.EventRepository;

//Enthälten allgemeine Routen, die keinem Controller direkt zuzuordnen sind.
@Controller
public class HomeController {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private CourseRepository courseRepository;

    @RequestMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(Model model) {

        return "index";
    }

    // Gibt Listenansicht (Plan) der Kurse zurück
    @GetMapping("/events/schedule")
    public String showAllCourses(Model model) {
        model.addAttribute("eventsMorning", eventRepository.findAllMorningEvents());
        model.addAttribute("eventsAfternoon", eventRepository.findAllAfternoonEvents());
        model.addAttribute("eventsEvening", eventRepository.findAllEveningEvents());
        return "wochenplan";
    }

    @RequestMapping("/impressum")
    public String Impressum() {
        return "impressum";
    }

    @RequestMapping("/datenschutz")
    public String Datenschutz() {
        return "datenschutz";
    }

    // Gibt Listenansicht der Kurse zurück
    @GetMapping("/course/plan")
    public String showAllProducts(Model model) {
        model.addAttribute("courses", courseRepository.findAll());
        return "kursplan";
    }

}