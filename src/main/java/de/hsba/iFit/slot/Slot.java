package de.hsba.ifit.slot;

import java.util.List;

import javax.persistence.*;

import de.hsba.ifit.user.User;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Slot {
    @GeneratedValue
    @Id
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private Daytime daytime;

    @Getter
    @Setter
    private Weekday weekday;

    @ManyToMany(mappedBy = "slots")
    private List<User> users;

    public Slot(Weekday weekday, Daytime daytime) {
        this.weekday = weekday;
        this.daytime = daytime;
    }

}
