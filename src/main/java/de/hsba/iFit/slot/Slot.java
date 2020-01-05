package de.hsba.ifit.slot;

import java.util.List;

import javax.persistence.*;

import de.hsba.ifit.user.User;
import de.hsba.ifit.daytime.Daytime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
public class Slot {
    @GeneratedValue
    @Id
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private Weekday weekday;

    @Getter
    @Setter
    @ManyToOne
    private Daytime daytime;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "slots")
    private List<User> users;

    public Slot(Weekday weekday, Daytime daytime) {
        this.weekday = weekday;
        this.daytime = daytime;
    }

}
