package de.hsba.ifit.daytime;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import de.hsba.ifit.daytime.Daytime.DaytimeName;

import javax.transaction.Transactional;

import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class DaytimeService {

    private final DaytimeRepository daytimeRepository;

    public void Seed(DaytimeName name, LocalTime start, LocalTime end) {
        daytimeRepository.save(new Daytime(name, start, end));
    }

    public List<Daytime> findAll() {
        return daytimeRepository.findAll();
    }

    public Daytime getAccordingDaytime(LocalTime time) {
        for (Daytime daytime : daytimeRepository.findAll()) {

            if (time.isAfter(daytime.getStart())
                    || time.equals(daytime.getStart()) && time.isBefore(daytime.getEnd())) {
                return daytime;
            }

        }

        return null;
    }

}