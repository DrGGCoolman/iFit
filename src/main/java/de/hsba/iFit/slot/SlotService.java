package de.hsba.ifit.slot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import de.hsba.ifit.daytime.Daytime;
import de.hsba.ifit.daytime.DaytimeService;

import javax.transaction.Transactional;

import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class SlotService {

    private final SlotRepository slotRepository;
    private final DaytimeService daytimeService;

    public void Seed(Weekday weekday, Daytime daytime) {
        slotRepository.save(new Slot(weekday, daytime));
    }

    public List<Slot> findAll() {
        return slotRepository.findAll();
    }

    public Slot returnSlotForDayAndTime(Weekday day, LocalTime time) {

        Daytime accordingDaytime = daytimeService.getAccordingDaytime(time);

        Slot fittingSlot = slotRepository.findByWeekdayAndDaytime(day, accordingDaytime).orElse(null);

        return fittingSlot;

    }

}