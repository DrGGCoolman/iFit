package de.hsba.ifit.web;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import de.hsba.ifit.course.Course;
import de.hsba.ifit.event.Room;
import de.hsba.ifit.slot.Weekday;
import de.hsba.ifit.user.User;



public class EventForm {

    @NotNull(message = "Bitte einen Startzeit eingeben")
    @Getter
    @Setter
    private LocalTime startAt;

    @NotNull(message = "Bitte einen Raum wählen")
    @Getter
    @Setter
    private Room room;

    @NotNull(message = "Bitte einen Wochentag wählen")
    @Getter
    @Setter
    private Weekday weekday;

    @NotNull(message = "Bitte einen Kurs wählen")
    @Getter
    @Setter
    private Course course;

    @NotNull(message = "Bitte einen Trainer wählen")
    @Getter
    @Setter
    private User user;
}
