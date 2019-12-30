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

    @RequestMapping("/index")
    public String index(Model model) {

        return "index";
    }

}