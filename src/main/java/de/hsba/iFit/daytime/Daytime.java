package de.hsba.ifit.daytime;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.*;

import de.hsba.ifit.slot.Slot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class Daytime {
    @GeneratedValue
    @Id
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @Column(unique = true)
    private String name;

    @Getter
    @Setter
    private LocalTime start;

    @Getter
    @Setter
    private LocalTime end;

    @OneToMany(mappedBy = "daytime")
    private List<Slot> slots;

    public Daytime(String name, LocalTime start, LocalTime end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

}
