package de.hsba.ifit.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//Enthälten allgemeine Routen, die keinem Controller direkt zuzuordnen sind.
@Controller
public class UserController {

    @RequestMapping("/login")
    public String login(Model model) {

        return "user/login";
    }

    @RequestMapping("/trainer/appointments")
    public String myAppointments(Model model) {

        return "user/meine-termine";
    }

    @RequestMapping("user/settings")
    public String userSettings(Model model) {

        return "user/settings";
    }



}