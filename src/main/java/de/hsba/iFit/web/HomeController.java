package de.hsba.ifit.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//Enthälten allgemeine Routen, die keinem Controller direkt zuzuordnen sind.
@Controller
public class HomeController {

    @RequestMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @RequestMapping("/login")
    public String login(Model model) {

        return "anonymous/login";
    }

    @RequestMapping("/index")
    public String index(Model model) {

        return "index";
    }

    @RequestMapping("/trainer/index")
    public String trainerIndex(Model model) {

        return "trainer/index";
    }

    @RequestMapping("/admin/index")
    public String adminIndex(Model model) {

        return "admin/index";
    }

    @RequestMapping("/owner/trainer/create")
    public String createTrainer(Model model) {

        return "owner/createTrainer";
    }

    @RequestMapping("/owner/classes/create")
    public String createClasses(Model model) {

        return "owner/createClasses";
    }
}