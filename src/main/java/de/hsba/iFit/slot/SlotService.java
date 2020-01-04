package de.hsba.ifit.slot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class SlotService {

    private final SlotRepository slotRepository;

    @PostConstruct
    void initialSeed() {
        if (slotRepository.count() == 0) {

            createSlot(Weekday.MO, Daytime.MORNING);
            createSlot(Weekday.MO, Daytime.NOON);
            createSlot(Weekday.MO, Daytime.AFTERNOON);
            createSlot(Weekday.MO, Daytime.EVENING);

            createSlot(Weekday.TU, Daytime.MORNING);
            createSlot(Weekday.TU, Daytime.NOON);
            createSlot(Weekday.TU, Daytime.AFTERNOON);
            createSlot(Weekday.TU, Daytime.EVENING);

            createSlot(Weekday.WE, Daytime.MORNING);
            createSlot(Weekday.WE, Daytime.NOON);
            createSlot(Weekday.WE, Daytime.AFTERNOON);
            createSlot(Weekday.WE, Daytime.EVENING);

            createSlot(Weekday.TH, Daytime.MORNING);
            createSlot(Weekday.TH, Daytime.NOON);
            createSlot(Weekday.TH, Daytime.AFTERNOON);
            createSlot(Weekday.TH, Daytime.EVENING);

            createSlot(Weekday.FR, Daytime.MORNING);
            createSlot(Weekday.FR, Daytime.NOON);
            createSlot(Weekday.FR, Daytime.AFTERNOON);
            createSlot(Weekday.FR, Daytime.EVENING);

            createSlot(Weekday.SA, Daytime.MORNING);
            createSlot(Weekday.SA, Daytime.NOON);
            createSlot(Weekday.SA, Daytime.AFTERNOON);
            createSlot(Weekday.SA, Daytime.EVENING);

            createSlot(Weekday.SU, Daytime.MORNING);
            createSlot(Weekday.SU, Daytime.NOON);
            createSlot(Weekday.SU, Daytime.AFTERNOON);
            createSlot(Weekday.SU, Daytime.EVENING);

        }
    }

    private void createSlot(Weekday weekday, Daytime daytime) {
        slotRepository.save(new Slot(weekday, daytime));
    }

    public List<Slot> findAll() {
        return slotRepository.findAll();
    }

}