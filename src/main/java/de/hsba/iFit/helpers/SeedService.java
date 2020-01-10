package de.hsba.ifit.helpers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import de.hsba.ifit.course.*;
import de.hsba.ifit.daytime.DaytimeRepository;
import de.hsba.ifit.daytime.DaytimeService;
import de.hsba.ifit.event.Event;
import de.hsba.ifit.event.EventRepository;
import de.hsba.ifit.event.EventService;
import de.hsba.ifit.event.Room;
import de.hsba.ifit.slot.Slot;
import de.hsba.ifit.slot.SlotRepository;
import de.hsba.ifit.slot.SlotService;
import de.hsba.ifit.slot.Weekday;
import de.hsba.ifit.user.User;
import de.hsba.ifit.user.UserRepository;
import de.hsba.ifit.user.UserService;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class SeedService {

        private final EventRepository eventRepository;
        private final UserRepository userRepository;
        private final CourseRepository courseRepository;
        private final DaytimeRepository daytimeRepository;
        private final SlotRepository slotRepository;

        private final CourseService courseService;
        private final UserService userService;
        private final EventService eventService;
        private final DaytimeService daytimeService;
        private final SlotService slotService;

        @PostConstruct
        void initialSeed() {

                // DAYTIME
                if (daytimeRepository.count() == 0) {
                        daytimeService.Seed("Morgens", LocalTime.of(8, 0), LocalTime.of(12, 0));
                        daytimeService.Seed("Nachmittags", LocalTime.of(12, 0), LocalTime.of(16, 0));
                        daytimeService.Seed("Abends", LocalTime.of(16, 0), LocalTime.of(20, 0));
                }

                // SlOTS
                if (slotRepository.count() == 0) {
                        slotService.Seed(Weekday.MO, daytimeRepository.findByName("Morgens"));
                        slotService.Seed(Weekday.MO, daytimeRepository.findByName("Nachmittags"));
                        slotService.Seed(Weekday.MO, daytimeRepository.findByName("Abends"));
                        slotService.Seed(Weekday.TU, daytimeRepository.findByName("Morgens"));
                        slotService.Seed(Weekday.TU, daytimeRepository.findByName("Nachmittags"));
                        slotService.Seed(Weekday.TU, daytimeRepository.findByName("Abends"));
                        slotService.Seed(Weekday.WE, daytimeRepository.findByName("Morgens"));
                        slotService.Seed(Weekday.WE, daytimeRepository.findByName("Nachmittags"));
                        slotService.Seed(Weekday.WE, daytimeRepository.findByName("Abends"));
                        slotService.Seed(Weekday.TH, daytimeRepository.findByName("Morgens"));
                        slotService.Seed(Weekday.TH, daytimeRepository.findByName("Nachmittags"));
                        slotService.Seed(Weekday.TH, daytimeRepository.findByName("Abends"));
                        slotService.Seed(Weekday.FR, daytimeRepository.findByName("Morgens"));
                        slotService.Seed(Weekday.FR, daytimeRepository.findByName("Nachmittags"));
                        slotService.Seed(Weekday.FR, daytimeRepository.findByName("Abends"));
                        slotService.Seed(Weekday.SA, daytimeRepository.findByName("Morgens"));
                        slotService.Seed(Weekday.SA, daytimeRepository.findByName("Nachmittags"));
                        slotService.Seed(Weekday.SA, daytimeRepository.findByName("Abends"));
                        slotService.Seed(Weekday.SU, daytimeRepository.findByName("Morgens"));
                        slotService.Seed(Weekday.SU, daytimeRepository.findByName("Nachmittags"));
                        slotService.Seed(Weekday.SU, daytimeRepository.findByName("Abends"));
                }

                // NUTZER
                if (userRepository.count() == 0) {
                        userService.Seed("Anne", "Baum", "anne.baum", "password", User.USER_ROLE);
                        userService.Seed("Benedikt", "Müller", "benedikt.müller", "password", User.USER_ROLE);
                        userService.Seed("Charlotte", "Tulpe", "charlotte.tulpe", "password", User.USER_ROLE);
                        userService.Seed("Zoe", "Richter", "zoe.richter", "password", User.USER_ROLE);
                        userService.Seed("admin", "admin", "admin", "admin", User.ADMIN_ROLE);

                        // Testsettings für User anlegen

                        User anne = userRepository.findByName("anne.baum");

                        List<Slot> annesSlots = new ArrayList<>();
                        annesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.MO, daytimeRepository.findByName("Morgens"))
                                        .orElseThrow());
                        annesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.SU, daytimeRepository.findByName("Abends"))
                                        .orElseThrow());
                        annesSlots.add(slotRepository.findByWeekdayAndDaytime(Weekday.MO,
                                        daytimeRepository.findByName("Nachmittags")).orElseThrow());

                        anne.setSlots(annesSlots);
                        userRepository.save(anne);

                }

                // KURSE
                if (courseRepository.count() == 0) {
                        courseService.Seed("Jumping Fit", "Effektive Kalorienverbrennung"
                                        + " Beim Jumping Fitness Workout sind über 400 Muskeln im Einsatz, viel mehr als bei vergleichbaren Ausdauersportarten. Jumping Fitness ist deutlich effektiver als Joggen und schont gleichzeitig die Gelenke."
                                        + " Effizientes Training"
                                        + " Bauch, Beine, Po – Jumping Fitness stärkt alle Körperpartien. Außerdem verbessern die dynamischen Übungen deine Ausdauer und beugen Rückenproblemen vor."
                                        + " Perfekt gegen Stress"
                                        + "Jumping Fitness macht glücklich, denn bei diesem Workout werden Glückshormone ausgeschüttet! Kein Wunder, Jumping Fitness bietet ein tolles Gruppengefühl und ein mitreißendes Training zu energetischer Musik."
                                        + "Für jeden geeignet"
                                        + "Gewicht, Alter oder Leistungsniveau sind für Einsteiger nebensächlich. Hauptsache, du willst Spaß haben! Sprünge und Techniken sind leicht zu lernen und garantieren auch Anfängern schnelle Erfolgserlebnisse.",
                                        Category.BODYWEIGHT, TargetGroup.RELAXED, 60);

                        courseService.Seed("Hantel Workout",
                                        "In der Krankengymnastik am Gerät werden medizinische Trainingsgeräte"
                                                        + "eingesetzt um Kraft, Ausdauer, Beweglichkeit und Koordination des Patienten zu verbessern.",
                                        Category.WEIGHTS, TargetGroup.REHA, 60);

                        courseService.Seed("Krankengymnastik am Gerät",
                                        "Das Hanteltraining bietet einen großen Abwechslungsreichtum beim Krafttraining und verbessert"
                                                        + " zusätzlich die intermuskuläre Koordination des Körpers. Zum Einsatz kommen Freihanteln wie die Kurzhantel"
                                                        + " oder Langhantel mit unterschiedlichem Gewicht. Durch das Training werden die Muskelfasern mehr aktiviert als bei einem normalen Bewegungsablauf"
                                                        + " und es lassen sich mit nur einer Übung mehrere Muskelgruppen fördern und ausbilden. Beim Training stehen unzählige Hanteln der unterschiedlichsten "
                                                        + " für eine jeweils optimale Übung zur Verfügung.",
                                        Category.WEIGHTS, TargetGroup.RELAXED, 60);

                }
                // TERMINE
                if (eventRepository.count() == 0) {
                        eventService.Seed(LocalTime.of(12, 05),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("zoe.richter"), Room.GYM2, Weekday.MO);

                        eventService.Seed(LocalTime.of(13, 15), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("zoe.richter"), Room.GYM2, Weekday.MO);

                        eventService.Seed(LocalTime.of(15, 15), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("zoe.richter"), Room.GYM1, Weekday.MO);

                        eventService.Seed(LocalTime.of(8, 15), courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("zoe.richter"), Room.GYM2, Weekday.MO);

                        eventService.Seed(LocalTime.of(9, 45), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("zoe.richter"), Room.GYM1, Weekday.MO);

                        eventService.Seed(LocalTime.of(15, 50), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("zoe.richter"), Room.GYM2, Weekday.MO);

                        eventService.Seed(LocalTime.of(16, 15),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("zoe.richter"), Room.GYM2, Weekday.MO);

                        eventService.Seed(LocalTime.of(19, 45), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("zoe.richter"), Room.GYM2, Weekday.MO);

                        eventService.Seed(LocalTime.of(20, 15), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("zoe.richter"), Room.GYM1, Weekday.MO);

                        eventService.Seed(LocalTime.of(13, 45), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("zoe.richter"), Room.GYM2, Weekday.TU);

                        eventService.Seed(LocalTime.of(10, 15), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("zoe.richter"), Room.GYM1, Weekday.TU);

                        eventService.Seed(LocalTime.of(12, 55), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("zoe.richter"), Room.GYM1, Weekday.TU);

                        eventService.Seed(LocalTime.of(18, 55), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("admin"), Room.GYM1, Weekday.WE);

                        eventService.Seed(LocalTime.of(19, 20), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("admin"), Room.GYM1, Weekday.TU);
                }
        }

        public List<Event> findAll() {
                return eventRepository.findAll();
        }

}