package de.hsba.ifit.filter;

import org.springframework.stereotype.Component;

import de.hsba.ifit.course.Course;
import de.hsba.ifit.daytime.Daytime;
import de.hsba.ifit.slot.Weekday;
import de.hsba.ifit.user.User;
import lombok.Getter;
import lombok.Setter;

@Component
public class Filter {

    @Getter
    @Setter
    private User selectedUser;
    @Getter
    @Setter
    private Weekday selectedWeekday;
    @Getter
    @Setter
    private Daytime selectedDaytime;
    @Getter
    @Setter
    private Course selectedCourse;

}