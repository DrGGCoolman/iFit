package de.hsba.ifit.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//Enthälten allgemeine Routen, die keinem Controller direkt zuzuordnen sind.
@Controller
public class TerminController {

    // TODO: Naming überarbeiten ( deutsch/englisch)

    @RequestMapping("/owner/termine/create")
    public String createTrainer(Model model) {

        return "termin/termin-create";
    }

    @RequestMapping("/owner/termine/details")
    public String showTrainerDetails(Model model) {

        return "termin/termin-details";
    }

    @RequestMapping("/owner/termine/edit")
    public String editTrainer(Model model) {

        return "termin/termin-edit";
    }

    @RequestMapping("/owner/termine")
    public String showTrainers(Model model) {

        return "termin/termin-liste";
    }

}