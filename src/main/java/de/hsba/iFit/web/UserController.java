package de.hsba.ifit.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.hsba.ifit.course.CourseRepository;
import de.hsba.ifit.daytime.DaytimeRepository;
import de.hsba.ifit.daytime.Daytime.DaytimeName;
import de.hsba.ifit.slot.SlotRepository;
import de.hsba.ifit.user.User;
import de.hsba.ifit.user.UserRepository;

//Enthälten allgemeine Routen, die keinem Controller direkt zuzuordnen sind.
@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SlotRepository slotRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private DaytimeRepository daytimeRepository;

    @RequestMapping("/login")
    public String login(Model model) {

        return "user/login";
    }

    // Gibt Login-Ansicht zurück. Stellt Fehlermeldung bei fehlgeschlagenem Login
    // dar.
    @GetMapping("/login")
    public String login(@RequestParam(required = false) boolean error, Model model,
            @RequestParam(required = false) Integer id) {
        String msg = error ? "Bitte Passwort und Nutzernamen prüfen!" : "";
        model.addAttribute("err", error);
        model.addAttribute("msg", msg);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return auth instanceof AnonymousAuthenticationToken ? "user/login" : "redirect:/";

    }

    @RequestMapping("/trainer/events")
    public String myAppointments(Model model) {

        User currUser = User.getCurrentUser();

        User user = userRepository.findById(currUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user"));
        model.addAttribute("user", user);
        return "user/trainer-events";
    }

    // Aufruf der Kurs-Beaarbeiten ansicht.
    @GetMapping("trainer/work")
    public String showUpdateForm(Model model) {
        User currUser = User.getCurrentUser();

        User user = userRepository.findById(currUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user"));
        model.addAttribute("user", user);
        model.addAttribute("slots", slotRepository.findAll());
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("isUpdate", true);

        model.addAttribute(DaytimeName.MORGENS + "Slots", slotRepository.findByDaytimeName(DaytimeName.MORGENS));
        model.addAttribute(DaytimeName.MITTAGS + "Slots", slotRepository.findByDaytimeName(DaytimeName.MITTAGS));
        model.addAttribute(DaytimeName.ABENDS + "Slots", slotRepository.findByDaytimeName(DaytimeName.ABENDS));

        model.addAttribute("myWork", slotRepository.findByUsersId(currUser.getId()));

        model.addAttribute("daytimes", daytimeRepository.findAll());

        return "user/trainer-work";
    }

    // Behandelt das Bearbeiten eines Kurses. Validiert das Kurs-Bearbeiten
    // formular.
    @PostMapping("/trainer/work/update/{id}")
    public String updateCourse(@PathVariable("id") Integer id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "redirect:" + id.toString();
        }

        User currUser = User.getCurrentUser();
        user.firstname = currUser.firstname;
        user.lastname = currUser.lastname;
        user.setPassword(currUser.getPassword());
        user.setName(currUser.getName());
        user.setRole(currUser.getRole());
        user.setCourses(currUser.getCourses());

        userRepository.save(user);

        // return "redirect:/kurs/" + id.toString();
        return "redirect:/trainer/events";
    }

    // Aufruf der Kurs-Beaarbeiten ansicht.
    @GetMapping("trainer/courses")
    public String showCourseForm(Model model) {
        User currUser = User.getCurrentUser();

        User user = userRepository.findById(currUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user"));
        model.addAttribute("user", user);
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("isUpdate", true);
        model.addAttribute("myCourses", courseRepository.findByUsersId(currUser.getId()));

        return "user/trainer-courses";
    }

    // Behandelt das Bearbeiten eines Kurses. Validiert das Kurs-Bearbeiten
    // formular.
    @PostMapping("/trainer/courses/update/{id}")
    public String updateCourses(@PathVariable("id") Integer id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "redirect:" + id.toString();
        }

        User currUser = User.getCurrentUser();
        user.firstname = currUser.firstname;
        user.lastname = currUser.lastname;
        user.setPassword(currUser.getPassword());
        user.setName(currUser.getName());
        user.setRole(currUser.getRole());
        user.setSlots(currUser.getSlots());

        userRepository.save(user);

        // return "redirect:/kurs/" + id.toString();
        return "redirect:/trainer/events";
    }

}