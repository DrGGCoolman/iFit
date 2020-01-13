package de.hsba.ifit.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.hsba.ifit.course.CourseRepository;
import de.hsba.ifit.daytime.DaytimeRepository;
import de.hsba.ifit.event.EventService;
import de.hsba.ifit.filter.Filter;
import de.hsba.ifit.filter.FilterService;
import de.hsba.ifit.slot.Weekday;
import de.hsba.ifit.user.UserRepository;

//Enthälten allgemeine Routen, die keinem Controller direkt zuzuordnen sind.
@Controller
public class HomeController {

    @Autowired
    private EventService eventService;
    @Autowired
    private DaytimeRepository daytimeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private FilterService filterService;
    @Autowired
    private Filter filter;

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

        model.addAttribute("filter", filter);
        model.addAttribute("daytimes", daytimeRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("courses", courseRepository.findAll());

        selectedWeekday = (selectedWeekday == null) ? Weekday.MO : selectedWeekday;
        model.addAttribute("eventsMorning", eventService.findAllMorningEventsForWeekday(selectedWeekday));
        model.addAttribute("eventsAfternoon", eventService.findAllAfternoonEventsForWeekday(selectedWeekday));
        model.addAttribute("eventsEvening", eventService.findAllEveningEventsForWeekday(selectedWeekday));
        model.addAttribute("selectedWeekday", selectedWeekday);

        return "schedule";
    }

    @PostMapping(path = "/filtered-events")
    public String showFilteredEvents(@ModelAttribute("filter") Filter filter, BindingResult bindingResult,
            Model model) {

        model.addAttribute("daytimes", daytimeRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("events", filterService.getFilteredEvents(filter));
        return "filtered-events";
    }

    @RequestMapping("/information")
    public String Impressum() {
        return "information";
    }

    @RequestMapping("/privacy")
    public String Datenschutz() {
        return "privacy";
    }

}