package de.hsba.ifit.slot;

import java.util.List;

import javax.persistence.*;

import de.hsba.ifit.user.User;
import lombok.Getter;

@Entity
public class Slot {
    @GeneratedValue
    @Id
    @Getter
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Daytime daytime;

    @Enumerated(EnumType.STRING)
    private Weekday weekday;

    @ManyToMany(mappedBy = "slots")
    private List<User> users;

}

enum Daytime {Vormittags, Mittags, Nachmittags, Abends};

enum Weekday {Montag, Dienstag, Mittwoch, Donnerstag, Freitag, Samstag, Sonntag};
