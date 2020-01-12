package de.hsba.ifit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import de.hsba.ifit.event.Event;
import de.hsba.ifit.event.EventRepository;

//Enthälten allgemeine Routen, die keinem Controller direkt zuzuordnen sind.
@Controller
public class HomeController {

    @Autowired
    private EventRepository eventRepository;

    @RequestMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(Model model) {

        return "index";
    }

    // Gibt Listenansicht (Plan) der Kurse zurück
    @GetMapping("/schedule")
    public String showAllCourses(Model model) {
        model.addAttribute("eventsMorning", eventRepository.findAllMorningEvents());
        model.addAttribute("eventsAfternoon", eventRepository.findAllAfternoonEvents());
        model.addAttribute("eventsEvening", eventRepository.findAllEveningEvents());
        return "schedule";
    }

    @GetMapping("/schedule/{id}")
    public String showEventDetails(@PathVariable("id") Integer id, Model model) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ProductType Id:" + id));
        model.addAttribute("event", event);
        return "event/event-details";
    }

    @RequestMapping("/information")
    public String Impressum() {
        return "information";
    }

    @RequestMapping("/privacy")
    public String Datenschutz() {
        return "privacy";
    }

    // @GetMapping("/schedule")
    // public String filterWeekday(@RequestParam(value = "wochentag", required = false) String weekday, Model model) {
    //     model.addAttribute("events", eventRepository.findByWeekday(weekday));
    //     return "schedule";
    // }


}