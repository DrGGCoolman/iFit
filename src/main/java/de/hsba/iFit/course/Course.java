package de.hsba.ifit.course;

import javax.persistence.*;

import de.hsba.ifit.event.Event;
import de.hsba.ifit.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
public class Course {

    @GeneratedValue
    @Id
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @Basic(optional = false)
    @Column(unique = true)
    private String name;

    @Getter
    @Setter
    @Lob
    private String description;

    @Getter
    @Setter
    private Category category;

    @Getter
    @Setter
    private TargetGroup targetGroup;

    @Getter
    @Setter
    private Integer duration;

    @OneToMany(mappedBy = "course", orphanRemoval = true)
    private List<Event> events;

    @ManyToMany(mappedBy = "courses")
    private List<User> users;

    public Course(String name, String description, Category category, TargetGroup targetGroup, Integer duration) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.targetGroup = targetGroup;
        this.duration = duration;
    }

    // Durch die Dreiecksbeziehung zwischen User, Event und Kurs lässt sich
    // schwer eine Kaskade per Annotation festsetzen. Deshalb wird hier vor dem
    // löschen des Kurses die Verbindung Kurs-Trainer gelöst
    @PreRemove
    private void removeCoursessFromUsers() {
        for (User u : users) {
            u.getCourses().remove(this);
        }
    }

}
