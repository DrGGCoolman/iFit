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
import de.hsba.ifit.event.EventRepository;
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
    private EventRepository eventRepository;

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

    @GetMapping("/user/edit")
    public String showUserUpdateForm(Model model) {

        User currUser = User.getCurrentUser();

        User user = userRepository.findById(currUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user"));
        model.addAttribute("user", user);

        return "trainer/trainer-edit";
    }

    @RequestMapping("/user/appointments")
    public String myAppointments(Model model) {
        model.addAttribute("myEvents", eventRepository.findMyEvents());
        return "user/user-termine";
    }

    // Aufruf der Kurs-Beaarbeiten ansicht.
    @GetMapping("user/settings")
    public String showUpdateForm(Model model) {
        User currUser = User.getCurrentUser();

        User user = userRepository.findById(currUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user"));

        model.addAttribute("user", user);
        model.addAttribute("slots", slotRepository.findAll());
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("isUpdate", true);
        return "user/user-einstellungen";
    }

    // Behandelt das Bearbeiten eines Kurses. Validiert das Kurs-Bearbeiten
    // formular.
    @PostMapping("/user/settings/update/{id}")
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


        userRepository.save(user);

        // return "redirect:/kurs/" + id.toString();
        return "redirect:/owner/course/list";
    }

}