package de.hsba.ifit.event;

import javax.persistence.*;

import de.hsba.ifit.course.Course;
import de.hsba.ifit.room.Room;
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
    private Integer startAt;

    @ManyToOne
    private Course course;

    @ManyToOne
    private User user;

    @ManyToOne
    private Room room;

    public Event(Integer startAt, Course course, User user, Room room) {
        this.startAt = startAt;
        this.course = course;
        this.user = user;
        this.room = room;
    }

}