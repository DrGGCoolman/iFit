package de.hsba.ifit.slot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import de.hsba.ifit.daytime.Daytime;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class SlotService {

    private final SlotRepository slotRepository;

    public void Seed(Weekday weekday, Daytime daytime) {
        slotRepository.save(new Slot(weekday, daytime));
    }

    public List<Slot> findAll() {
        return slotRepository.findAll();
    }

}