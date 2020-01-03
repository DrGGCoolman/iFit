package de.hsba.ifit.course;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;

    @PostConstruct
    void initialSeed() {
        if (courseRepository.count() == 0) {
            createCourse("Jumping Fit", "Effektive Kalorienverbrennung"
                    + " Beim Jumping Fitness Workout sind über 400 Muskeln im Einsatz, viel mehr als bei vergleichbaren Ausdauersportarten. Jumping Fitness ist deutlich effektiver als Joggen und schont gleichzeitig die Gelenke."
                    + " Effizientes Training"
                    + " Bauch, Beine, Po – Jumping Fitness stärkt alle Körperpartien. Außerdem verbessern die dynamischen Übungen deine Ausdauer und beugen Rückenproblemen vor."
                    + " Perfekt gegen Stress"
                    + "Jumping Fitness macht glücklich, denn bei diesem Workout werden Glückshormone ausgeschüttet! Kein Wunder, Jumping Fitness bietet ein tolles Gruppengefühl und ein mitreißendes Training zu energetischer Musik."
                    + "Für jeden geeignet"
                    + "Gewicht, Alter oder Leistungsniveau sind für Einsteiger nebensächlich. Hauptsache, du willst Spaß haben! Sprünge und Techniken sind leicht zu lernen und garantieren auch Anfängern schnelle Erfolgserlebnisse.",
                    Category.BODYWEIGHT, "junge Frauen", 60);

        }
    }

    private void createCourse(String name, String description, Category category, String targetGroup, int duration) {
        courseRepository.save(new Course(name, description, category, targetGroup, duration));
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

}