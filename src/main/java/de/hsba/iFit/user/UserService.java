package de.hsba.ifit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import de.hsba.ifit.course.Course;
import de.hsba.ifit.slot.Slot;
import de.hsba.ifit.slot.SlotService;
import de.hsba.ifit.slot.Weekday;

import javax.transaction.Transactional;

import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SlotService slotService;

    public void Seed(String firstname, String lastname, String name, String password, String role) {
        userRepository.save(new User(firstname, lastname, name, passwordEncoder.encode(password), role));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findUsers() {
        return userRepository.findByRole(User.USER_ROLE);
    }

    public List<User> findFittingTrainer(Weekday day, LocalTime time, Course course) {

        // Hier wird aus Tag und Zeit ein Slot ausgewählt.

        Slot equivalentSlot = slotService.returnSlotForDayAndTime(day, time);

        List<User> fittingTrainer = userRepository.findBySlotsId(equivalentSlot.getId());

        return fittingTrainer;

    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findUser(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

}