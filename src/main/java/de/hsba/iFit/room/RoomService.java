package de.hsba.ifit.room;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class RoomService {

    private final RoomRepository roomRepository;

    @PostConstruct
    void initialSeed() {
        if (roomRepository.count() == 0) {
        
            createRoom("Schwimmbad");
            createRoom("Fitnessraum 1");
            createRoom("Fitnessraum 2");
            createRoom("Tanzstudio");

        }
    }

    private void createRoom(String name) {
        roomRepository.save(new Room(name));
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

}