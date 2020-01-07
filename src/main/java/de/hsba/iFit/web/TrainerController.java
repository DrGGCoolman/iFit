package de.hsba.ifit.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import de.hsba.ifit.user.User;
import de.hsba.ifit.user.UserRepository;
import de.hsba.ifit.user.UserService;
import lombok.RequiredArgsConstructor;

//Enthälten allgemeine Routen, die keinem Controller direkt zuzuordnen sind.
@RequiredArgsConstructor
@Controller
@RequestMapping("/owner/trainer/")
public class TrainerController {

    @Autowired
    private UserRepository userRepository;

    private final UserService userService;
    private final FormAssembler formAssembler;

    // Gibt Daten zu einem bestimmten Course zurück. Benötigt dazu beim Aufruf
    // eine Id.
    // Die Daten stammen aus der Datenbank und werden über das entsprechen
    // Repository bezogen.
    @GetMapping("{id}")
    public String showTrainerDetails(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ProductType Id:" + id));
        model.addAttribute("user", user);
        return "trainer/trainer-detail";
    }

    // Aufruf der Kurs-Anlegen ansicht.
    @GetMapping("create")
    public String showCreateFrom(Model model) {
        model.addAttribute("trainerForm", new TrainerForm());
        return "trainer/trainer-create";
    }

    // Behandelt das Anlegen eines Produktes. Validiert das Kurs-Anlegen
    // formular.
    @PostMapping("add")
    public String addCourse(@ModelAttribute("trainerForm") @Valid TrainerForm trainerForm, BindingResult result) {
        if (result.hasErrors()) {
            return "trainer/trainer-create";
        }
        userService.save(formAssembler.update(new User(), trainerForm));
        return "redirect:/owner/trainer/list";
    }

    // Aufruf der Kurs-Beaarbeiten ansicht.
    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.findUser(id);
        model.addAttribute("trainerForm", formAssembler.toForm(user));
        model.addAttribute("isUpdate", true);

        return "trainer/trainer-edit";
    }

    // Behandelt das Bearbeiten eines Kurses. Validiert das Kurs-Bearbeiten
    // formular.
    @PostMapping("update/{id}")
    public String updateTrainer(@PathVariable("id") Integer id, @Valid TrainerForm form, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "trainer/trainer-edit";
        }
        User user = userService.findUser(id);
        userService.save(formAssembler.update(user, form));
        // return "redirect:/kurs/" + id.toString();
        return "redirect:/owner/trainer/list";
    }

    // Behandelt das Löschen eines Kurses.
    @GetMapping("/delete/{id}")
    public String deleteTrainer(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ProductType Id:" + id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/owner/trainer/list ";
    }

    @GetMapping("list")
    public String showAllTrainers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "trainer/trainer-liste";

    }
}