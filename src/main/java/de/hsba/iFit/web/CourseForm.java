package de.hsba.ifit.web;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import de.hsba.ifit.course.Category;
import de.hsba.ifit.course.TargetGroup;

@Getter
@Setter
public class CourseForm {

    @NotEmpty(message = "Bitte einen Namen eingeben")
    private String name;

    @NotEmpty(message = "Bitte eine Beschreibung eingeben")
    private String description;

    @NotNull(message = "Bitte eine Kategorie wählen")
    private Category category;

    @NotNull(message = "Bitte eine Zielgruppe wählen")
    private TargetGroup targetGroup;

    @NotNull(message = "Bitte eine Dauer eingeben")
    private Integer duration;
}
