package de.hsba.ifit.course;

import lombok.RequiredArgsConstructor;
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
                    Category.BODYWEIGHT, TargetGroup.RELAXED, 60);

            createCourse("Hantel Workout", "In der Krankengymnastik am Gerät werden medizinische Trainingsgeräte"
                    + "eingesetzt um Kraft, Ausdauer, Beweglichkeit und Koordination des Patienten zu verbessern.",
                    Category.WEIGHTS, TargetGroup.REHA, 60);

            createCourse("Krankengymnastik am Gerät",
                    "Das Hanteltraining bietet einen großen Abwechslungsreichtum beim Krafttraining und verbessert"
                            + " zusätzlich die intermuskuläre Koordination des Körpers. Zum Einsatz kommen Freihanteln wie die Kurzhantel"
                            + " oder Langhantel mit unterschiedlichem Gewicht. Durch das Training werden die Muskelfasern mehr aktiviert als bei einem normalen Bewegungsablauf"
                            + " und es lassen sich mit nur einer Übung mehrere Muskelgruppen fördern und ausbilden. Beim Training stehen unzählige Hanteln der unterschiedlichsten "
                            + " für eine jeweils optimale Übung zur Verfügung.",
                    Category.WEIGHTS, TargetGroup.RELAXED, 60);

        }
    }

    private void createCourse(String name, String description, Category category, TargetGroup targetGroup,
            int duration) {
        courseRepository.save(new Course(name, description, category, targetGroup, duration));
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

}