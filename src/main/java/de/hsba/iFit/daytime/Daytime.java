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
    private DaytimeName name;

    @Getter
    @Setter
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime start;

    @Getter
    @Setter
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime end;

    @OneToMany(mappedBy = "daytime")
    private List<Slot> slots;

    public Daytime(DaytimeName name, LocalTime start, LocalTime end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public enum DaytimeName {
        MORGENS("Morgens"), MITTAGS("Nachmittags"), ABENDS("Abends");

        private final String displayValue;

        private DaytimeName(String displayValue) {
            this.displayValue = displayValue;
        }

        public String getDisplayValue() {
            return displayValue;
        }
    }
}
