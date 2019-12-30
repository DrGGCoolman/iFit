package de.hsba.ifit.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//Enthälten allgemeine Routen, die keinem Controller direkt zuzuordnen sind.
@Controller
public class TrainerController {

    @RequestMapping("/owner/trainers/create")
    public String createTrainer(Model model) {

        return "trainer/trainer-create";
    }

    @RequestMapping("/owner/trainers/details")
    public String showTrainerDetails(Model model) {

        return "trainer/trainer-details";
    }

    @RequestMapping("/owner/trainers/edit")
    public String editTrainer(Model model) {

        return "trainer/trainer-edit";
    }

    @RequestMapping("/owner/trainers")
    public String showTrainers(Model model) {

        return "trainer/trainer-liste";
    }

}