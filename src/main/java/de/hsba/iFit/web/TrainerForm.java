package de.hsba.ifit.web;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import de.hsba.ifit.course.Course;
import de.hsba.ifit.event.Event;

public class TrainerForm {

    @NotEmpty(message = "Bitte einen Nachnamen eingeben")
    @Getter
    @Setter
    private String lastname;

    @NotEmpty(message = "Bitte einen Vornamen eingeben")
    @Getter
    @Setter
    private String firstname;

    @NotEmpty(message = "Bitte einen Benutzernamen eingeben")
    @Getter
    @Setter
    private String name;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}", message = "Mind.: 8 Zeichen, eine Zahl, einen Groß- und einen Kleinbuchstabe")
    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String role;

    @Getter
    @Setter
    private List<Event> events;

    @Getter
    @Setter
    private List<Course> courses;

}
