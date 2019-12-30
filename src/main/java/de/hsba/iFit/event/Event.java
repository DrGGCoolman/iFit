package de.hsba.ifit.event;

import javax.persistence.*;

import de.hsba.ifit.course.Course;
import de.hsba.ifit.room.Room;
import de.hsba.ifit.user.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
public class Event {
    @GeneratedValue
    @Id
    @Getter
    private Integer id;
    @Getter
    @Setter
    private LocalTime startAt;


    @ManyToOne
    private Course course;

    @ManyToOne
    private User user;

    @ManyToOne
    private Room room;

} 