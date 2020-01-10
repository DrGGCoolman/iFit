package de.hsba.ifit.daytime;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime start;

    @Getter
    @Setter
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime end;

    @OneToMany(mappedBy = "daytime")
    private List<Slot> slots;

    public Daytime(String name, LocalTime start, LocalTime end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

}
