package de.hsba.ifit.event;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

import de.hsba.ifit.course.Course;
import de.hsba.ifit.slot.Weekday;
import de.hsba.ifit.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class Event {

    @GeneratedValue
    @Id
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startAt;

    @Getter
    @Setter
    private Room room;

    @Getter
    @Setter
    private Weekday weekday;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @Getter
    @Setter
    @ManyToOne
    private User user;

    @Transient
    @Getter
    @Setter
    private EventState eventState;

    public Event(LocalTime startAt, Course course, User user, Room room, Weekday weekday) {
        this.startAt = startAt;
        this.course = course;
        this.user = user;
        this.room = room;
        this.weekday = weekday;

    }

}