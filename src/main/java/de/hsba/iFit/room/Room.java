package de.hsba.ifit.room;

import javax.persistence.*;

import de.hsba.ifit.event.Event;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Room {
    @GeneratedValue
    @Id
    @Getter
    private Integer id;
    @Getter
    @Setter
    private String name;

    @OneToMany(mappedBy = "room")
    private List<Event> events;

}