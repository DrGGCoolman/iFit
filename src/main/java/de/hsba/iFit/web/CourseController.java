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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import de.hsba.ifit.course.Course;
import de.hsba.ifit.course.CourseService;
import de.hsba.ifit.course.CourseRepository;
import lombok.RequiredArgsConstructor;

//Behandelt alle Anfragen bzgl. der ProductTypes. Alle routen werden unter /products/* gruppiert.
@RequiredArgsConstructor
@Controller
@RequestMapping("/owner/course/")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    private final CourseService courseService;
    private final FormAssembler formAssembler;

    // Aufruf der Kurs-Anlegen ansicht.
    @GetMapping("create")
    public String showCreateFrom(Model model) {
        model.addAttribute("courseForm", new CourseForm());
        return "course/course-create";
    }

    // Behandelt das Anlegen eines Produktes. Validiert das Kurs-Anlegen
    // formular.
    @PostMapping("add")
    public String addCourse(@ModelAttribute("courseForm") @Valid CourseForm courseForm, BindingResult result) {
        if (result.hasErrors()) {
            return "course/course-create";
        }

        courseService.save(formAssembler.update(new Course(), courseForm));
        return "redirect:/owner/course/list";
    }

    // Aufruf der Kurs-Beaarbeiten ansicht.
    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Course course = courseService.findCourse(id);
        model.addAttribute("courseForm", formAssembler.toForm(course));
        model.addAttribute("isUpdate", true);
        return "course/course-edit";
    }

    // Behandelt das Bearbeiten eines Kurses. Validiert das Kurs-Bearbeiten
    // formular.
    @PostMapping("update/{id}")
    public String updateCourse(@PathVariable("id") Integer id,
    @ModelAttribute("courseForm") @Valid CourseForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "course/course-edit";
        }
        Course course = courseService.findCourse(id);
        courseService.save(formAssembler.update(course, form));
        // return "redirect:/kurs/" + id.toString();
        return "redirect:/owner/course/list";
    }

    // Behandelt das Löschen eines Kurses.
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") int id, Model model) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ProductType Id:" + id));
        courseRepository.delete(course);
        model.addAttribute("courses", courseRepository.findAll());
        return "redirect:/owner/course/list ";
    }

    // Gibt Listenansicht der Kurse zurück
    @GetMapping("list")
    public String showAllProducts(Model model) {
        model.addAttribute("courses", courseRepository.findAll());
        return "course/course-list";
    }

}
