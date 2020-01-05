package de.hsba.ifit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.hsba.ifit.user.User;
import de.hsba.ifit.user.UserRepository;

//Enthälten allgemeine Routen, die keinem Controller direkt zuzuordnen sind.
@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

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
    public String showUpdateForm(Model model) {

        User currUser = User.getCurrentUser();

        User user = userRepository.findById(currUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user"));
        model.addAttribute("user", user);

        return "trainer/trainer-edit";
    }

    @RequestMapping("/user/appointments")
    public String myAppointments(Model model) {

        return "user/user-termine";
    }

    @RequestMapping("user/settings")
    public String userSettings(Model model) {

        return "user/user-einstellungen";
    }

}