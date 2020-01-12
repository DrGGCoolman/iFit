package de.hsba.ifit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.hsba.ifit.event.EventRepository;
import de.hsba.ifit.slot.Weekday;

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
    @GetMapping(path = "/schedule")
    public String showAllCourses(@RequestParam(required = false) Weekday selectedWeekday, Model model) {

        selectedWeekday = (selectedWeekday == null) ? Weekday.MO : selectedWeekday;
        model.addAttribute("eventsMorning", eventRepository.findAllMorningEventsForWeekday(selectedWeekday));
        model.addAttribute("eventsAfternoon", eventRepository.findAllAfternoonEventsForWeekday(selectedWeekday));
        model.addAttribute("eventsEvening", eventRepository.findAllEveningEventsForWeekday(selectedWeekday));

        return "schedule";
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
    // public String filterWeekday(@RequestParam(value = "wochentag", required =
    // false) String weekday, Model model) {
    // model.addAttribute("events", eventRepository.findByWeekday(weekday));
    // return "schedule";
    // }

}