package de.hsba.ifit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/class/termine")
    public String classTermine(Model model) {

        return "kurs-termine";
    }

    // Gibt Listenansicht (Plan) der Kurse zurück
    @GetMapping("/course/plan")
    public String showAllEvents(Model model) {
        model.addAttribute("events", eventRepository.findAll());
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

}