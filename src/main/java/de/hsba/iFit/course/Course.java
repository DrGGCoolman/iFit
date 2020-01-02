package de.hsba.ifit.course;

import javax.persistence.*;

import de.hsba.ifit.event.Event;
import de.hsba.ifit.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
public class Course {
    @GeneratedValue
    @Id
    @Getter
    private Integer id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String category;

    @Getter
    @Setter
    private String targetGroup;

    @Getter
    @Setter
    private Integer duration;

    @OneToMany(mappedBy = "course")
    private List<Event> events;

    @ManyToMany(mappedBy = "courses")
    private List<User> users;

    public Course(String name, String description, String category, String targetGroup, Integer duration) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.targetGroup = targetGroup;
        this.duration = duration;
    }

}





