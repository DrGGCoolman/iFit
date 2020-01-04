package de.hsba.ifit.event;

import javax.persistence.*;
import java.time.LocalTime;

import de.hsba.ifit.course.Course;
import de.hsba.ifit.user.User;
import lombok.Getter;
import lombok.Setter;


@Entity
public class Event {
    @GeneratedValue
    @Id
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private LocalTime startAt;
    @Getter
    @Setter
    private Room room;


    @ManyToOne
    private Course course;

    @ManyToOne
    private User user;


    public Event(LocalTime startAt, Course course, User user, Room room) {
        this.startAt = startAt;
        this.course = course;
        this.user = user;
        this.room = room;
    }

} 