package de.hsba.ifit.daytime;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class DaytimeService {

    private final DaytimeRepository daytimeRepository;

    public void Seed(String name, LocalTime start, LocalTime end) {
        daytimeRepository.save(new Daytime(name, start, end));
    }

    public List<Daytime> findAll() {
        return daytimeRepository.findAll();
    }

}