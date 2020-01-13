package de.hsba.ifit.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.hsba.ifit.course.CourseRepository;
import de.hsba.ifit.event.EventRepository;
import de.hsba.ifit.slot.SlotRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SlotRepository slotRepository;

    @BeforeEach
    public void setUp() {
        // delete all entries that have been created by the UserService's init method

        eventRepository.deleteAll();
        eventRepository.flush();
        courseRepository.deleteAll();
        courseRepository.flush();
        userRepository.deleteAll();
        userRepository.flush();
        slotRepository.deleteAll();
        slotRepository.flush();
    }

    @Test
    public void shouldFindByName() {

        // given
        User anne = new User("Roland", "Baum", "Roland.baum", "password", User.USER_ROLE);
        User bene = new User("Ralmund", "Müller", "Ralmund.müller", "password", User.ADMIN_ROLE);
        userRepository.save(anne);
        userRepository.save(bene);

        // then
        assertThat(userRepository.findByName("Roland.baum")).isEqualTo(anne);
        assertThat(userRepository.findByName("Ralmund.müller")).isEqualTo(bene);
        assertThat(userRepository.findByName("charly.bwon")).isNull();
    }

}
