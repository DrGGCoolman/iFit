package de.hsba.ifit.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//Enthälten allgemeine Routen, die keinem Controller direkt zuzuordnen sind.
@Controller
public class KursController {

    @RequestMapping("/owner/classes/create")
    public String createTrainer(Model model) {

        return "kurs/kurs-create";
    }

    @RequestMapping("/owner/classes/details")
    public String showTrainerDetails(Model model) {

        return "kurs/kurs-details";
    }

    @RequestMapping("/owner/classes/edit")
    public String editTrainer(Model model) {

        return "kurs/kurs-edit";
    }

    @RequestMapping("/classes")
    public String showTrainers(Model model) {

        return "kurs/kurs-liste";
    }

}