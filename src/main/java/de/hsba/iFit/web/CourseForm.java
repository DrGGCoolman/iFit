package de.hsba.ifit.web;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import de.hsba.ifit.course.Category;
import de.hsba.ifit.course.TargetGroup;

public class CourseForm {

    @NotEmpty(message = "Bitte einen Namen eingeben")
    @Getter
    @Setter
    private String name;

    @NotEmpty(message = "Bitte eine Beschreibung eingeben")
    @Getter
    @Setter
    private String description;

    @NotNull(message = "Bitte eine Kategorie wählen")
    @Getter
    @Setter
    private Category category;

    @NotNull(message = "Bitte eine Zielgruppe wählen")
    @Getter
    @Setter
    private TargetGroup targetGroup;

    @NotNull(message = "Bitte eine Dauer eingeben")
    @Getter
    @Setter
    private Integer duration;
}
