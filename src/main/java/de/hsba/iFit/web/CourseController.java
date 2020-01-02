package de.hsba.ifit.web;

/**
 * ProductTypeController
 */

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import de.hsba.ifit.course.Course;
import de.hsba.ifit.course.CourseRepository;

//Behandelt alle Anfragen bzgl. der ProductTypes. Alle routen werden unter /products/* gruppiert.
@Controller
@RequestMapping("/owner/course/")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    // Gibt Daten zu einem bestimmten ProductType zurück. Benötigt dazu beim Aufruf
    // eine Id.
    // Die Daten stammen aus der Datenbank und werden über das entsprechen
    // Repository bezogen.
    @GetMapping("{id}")
    public String showProductDetail(@PathVariable("id") Integer id, Model model) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ProductType Id:" + id));
        model.addAttribute("course", course);
        return "kurs/kurs-detail";
    }

    // Aufruf der Produkt-Anlegen ansicht.
    @GetMapping("create")
    public String showCreateFrom(Model model) {
        model.addAttribute("course", new Course());
        return "kurs/kurs-create";
    }

    // Behandelt das Anlegen eines Produktes. Validiert das Produkt-Anlegen
    // formular.
    @PostMapping("add")
    public String addProductType(@Valid Course course, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "kurs/kurs-create";
        }
        Course savedCourse = courseRepository.save(course);
        return "redirect:edit/" + savedCourse.getId().toString();
    }

    // Aufruf der Produkt-Beaarbeiten ansicht.
    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ProductType Id:" + id));
        model.addAttribute("isUpdate", true);
        model.addAttribute("course", course);
        return "kurs/kurs-edit";
    }

    // Behandelt das Bearbeiten eines Produktes. Validiert das Produkt-Bearbeiten
    // formular.
    @PostMapping("update/{id}")
    public String updateProductType(@PathVariable("id") Integer id, @Valid Course course, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            course.setId(id);
            return "redirect:" + id.toString();
        }

        courseRepository.save(course);

        return "redirect:/kurs/" + id.toString();
    }

    // Behandelt das Löschen eines Produktes.
    @GetMapping("/delete/{id}")
    public String deleteProductType(@PathVariable("id") int id, Model model) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ProductType Id:" + id));
        courseRepository.delete(course);
        model.addAttribute("courses", courseRepository.findAll());
        return "redirect:/kurs/kurs-list";
    }

    // Gibt Listenansicht der Farhzeuge zurück
    @GetMapping("list")
    public String showAllProducts(Model model) {
        model.addAttribute("course", courseRepository.findAll());
        return "kurs/kurs-liste";
    }

}
