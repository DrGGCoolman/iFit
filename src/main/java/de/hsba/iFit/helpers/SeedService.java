package de.hsba.ifit.helpers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import de.hsba.ifit.course.*;
import de.hsba.ifit.daytime.DaytimeRepository;
import de.hsba.ifit.daytime.DaytimeService;
import de.hsba.ifit.daytime.Daytime.DaytimeName;
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
                        daytimeService.Seed(DaytimeName.MORGENS, LocalTime.of(8, 0), LocalTime.of(12, 0));
                        daytimeService.Seed(DaytimeName.MITTAGS, LocalTime.of(12, 0), LocalTime.of(16, 0));
                        daytimeService.Seed(DaytimeName.ABENDS, LocalTime.of(16, 0), LocalTime.of(20, 0));
                }

                // SlOTS
                if (slotRepository.count() == 0) {
                        slotService.Seed(Weekday.MO, daytimeRepository.findByName(DaytimeName.MORGENS));
                        slotService.Seed(Weekday.MO, daytimeRepository.findByName(DaytimeName.MITTAGS));
                        slotService.Seed(Weekday.MO, daytimeRepository.findByName(DaytimeName.ABENDS));
                        slotService.Seed(Weekday.TU, daytimeRepository.findByName(DaytimeName.MORGENS));
                        slotService.Seed(Weekday.TU, daytimeRepository.findByName(DaytimeName.MITTAGS));
                        slotService.Seed(Weekday.TU, daytimeRepository.findByName(DaytimeName.ABENDS));
                        slotService.Seed(Weekday.WE, daytimeRepository.findByName(DaytimeName.MORGENS));
                        slotService.Seed(Weekday.WE, daytimeRepository.findByName(DaytimeName.MITTAGS));
                        slotService.Seed(Weekday.WE, daytimeRepository.findByName(DaytimeName.ABENDS));
                        slotService.Seed(Weekday.TH, daytimeRepository.findByName(DaytimeName.MORGENS));
                        slotService.Seed(Weekday.TH, daytimeRepository.findByName(DaytimeName.MITTAGS));
                        slotService.Seed(Weekday.TH, daytimeRepository.findByName(DaytimeName.ABENDS));
                        slotService.Seed(Weekday.FR, daytimeRepository.findByName(DaytimeName.MORGENS));
                        slotService.Seed(Weekday.FR, daytimeRepository.findByName(DaytimeName.MITTAGS));
                        slotService.Seed(Weekday.FR, daytimeRepository.findByName(DaytimeName.ABENDS));
                        slotService.Seed(Weekday.SA, daytimeRepository.findByName(DaytimeName.MORGENS));
                        slotService.Seed(Weekday.SA, daytimeRepository.findByName(DaytimeName.MITTAGS));
                        slotService.Seed(Weekday.SA, daytimeRepository.findByName(DaytimeName.ABENDS));
                        slotService.Seed(Weekday.SU, daytimeRepository.findByName(DaytimeName.MORGENS));
                        slotService.Seed(Weekday.SU, daytimeRepository.findByName(DaytimeName.MITTAGS));
                        slotService.Seed(Weekday.SU, daytimeRepository.findByName(DaytimeName.ABENDS));
                }

                // NUTZER
                if (userRepository.count() == 0) {
                        userService.Seed("Anne", "Baum", "anne.baum", "password", User.USER_ROLE);
                        userService.Seed("Benedikt", "Müller", "benedikt.müller", "password", User.USER_ROLE);
                        userService.Seed("Charlotte", "Tulpe", "charlotte.tulpe", "password", User.USER_ROLE);
                        userService.Seed("Zoe", "Richter", "zoe.richter", "password", User.USER_ROLE);
                        userService.Seed("Max", "Meier", "max.meier", "password", User.USER_ROLE);
                        userService.Seed("admin", "admin", "admin", "admin", User.ADMIN_ROLE);

                        // Testsettings für User anlegen

                        User admin = userRepository.findByName("admin");

                        List<Slot> adminsSlots = new ArrayList<>();

                        adminsSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.MO,
                                                        daytimeRepository.findByName(DaytimeName.MORGENS))
                                        .orElseThrow());

                        admin.setSlots(adminsSlots);

                        List<Course> adminsKurse = new ArrayList<>();

                        adminsKurse.add(courseRepository.findByName("Hantel Workout"));

                        admin.setCourses(adminsKurse);

                        userRepository.save(admin);

                        User anne = userRepository.findByName("anne.baum");

                        List<Slot> annesSlots = new ArrayList<>();

                        annesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.MO,
                                                        daytimeRepository.findByName(DaytimeName.MORGENS))
                                        .orElseThrow());
                        annesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.MO,
                                                        daytimeRepository.findByName(DaytimeName.MITTAGS))
                                        .orElseThrow());
                        annesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.MO,
                                                        daytimeRepository.findByName(DaytimeName.ABENDS))
                                        .orElseThrow());
                        annesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.TU,
                                                        daytimeRepository.findByName(DaytimeName.MORGENS))
                                        .orElseThrow());
                        annesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.TU,
                                                        daytimeRepository.findByName(DaytimeName.MITTAGS))
                                        .orElseThrow());
                        annesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.TU,
                                                        daytimeRepository.findByName(DaytimeName.ABENDS))
                                        .orElseThrow());
                        annesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.WE,
                                                        daytimeRepository.findByName(DaytimeName.MORGENS))
                                        .orElseThrow());
                        annesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.WE,
                                                        daytimeRepository.findByName(DaytimeName.MITTAGS))
                                        .orElseThrow());
                        annesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.WE,
                                                        daytimeRepository.findByName(DaytimeName.ABENDS))
                                        .orElseThrow());
                        annesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.SA,
                                                        daytimeRepository.findByName(DaytimeName.MORGENS))
                                        .orElseThrow());
                        annesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.SA,
                                                        daytimeRepository.findByName(DaytimeName.MITTAGS))
                                        .orElseThrow());
                        annesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.SA,
                                                        daytimeRepository.findByName(DaytimeName.ABENDS))
                                        .orElseThrow());
                        anne.setSlots(annesSlots);

                        List<Course> annesKurse = new ArrayList<>();

                        annesKurse.add(courseRepository.findByName("Jumping Fit"));
                        annesKurse.add(courseRepository.findByName("Krankengymnastik am Gerät"));
                        annesKurse.add(courseRepository.findByName("Hantel Workout"));
                        annesKurse.add(courseRepository.findByName("Zumba"));

                        anne.setCourses(annesKurse);

                        userRepository.save(anne);

                        User benedikt = userRepository.findByName("benedikt.müller");

                        List<Slot> benediktsSlots = new ArrayList<>();

                        benediktsSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.MO,
                                                        daytimeRepository.findByName(DaytimeName.MORGENS))
                                        .orElseThrow());
                        benediktsSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.MO,
                                                        daytimeRepository.findByName(DaytimeName.MITTAGS))
                                        .orElseThrow());
                        benediktsSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.MO,
                                                        daytimeRepository.findByName(DaytimeName.ABENDS))
                                        .orElseThrow());
                        benediktsSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.TU,
                                                        daytimeRepository.findByName(DaytimeName.MORGENS))
                                        .orElseThrow());
                        benediktsSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.TU,
                                                        daytimeRepository.findByName(DaytimeName.MITTAGS))
                                        .orElseThrow());
                        benediktsSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.TU,
                                                        daytimeRepository.findByName(DaytimeName.ABENDS))
                                        .orElseThrow());
                        benediktsSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.WE,
                                                        daytimeRepository.findByName(DaytimeName.MORGENS))
                                        .orElseThrow());
                        benediktsSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.WE,
                                                        daytimeRepository.findByName(DaytimeName.MITTAGS))
                                        .orElseThrow());
                        benediktsSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.WE,
                                                        daytimeRepository.findByName(DaytimeName.ABENDS))
                                        .orElseThrow());
                        benediktsSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.TH,
                                                        daytimeRepository.findByName(DaytimeName.MORGENS))
                                        .orElseThrow());
                        benediktsSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.TH,
                                                        daytimeRepository.findByName(DaytimeName.MITTAGS))
                                        .orElseThrow());
                        benediktsSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.SA,
                                                        daytimeRepository.findByName(DaytimeName.MORGENS))
                                        .orElseThrow());
                        benediktsSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.SA,
                                                        daytimeRepository.findByName(DaytimeName.MITTAGS))
                                        .orElseThrow());
                        benediktsSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.SA,
                                                        daytimeRepository.findByName(DaytimeName.ABENDS))
                                        .orElseThrow());
                        benedikt.setSlots(benediktsSlots);

                        List<Course> benediktsKurse = new ArrayList<>();

                        benediktsKurse.add(courseRepository.findByName("Hantel Workout"));

                        benedikt.setCourses(benediktsKurse);

                        userRepository.save(benedikt);

                        User charlotte = userRepository.findByName("charlotte.tulpe");

                        List<Slot> charlottesSlots = new ArrayList<>();
                        charlottesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.WE,
                                                        daytimeRepository.findByName(DaytimeName.MORGENS))
                                        .orElseThrow());
                        charlottesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.WE,
                                                        daytimeRepository.findByName(DaytimeName.MITTAGS))
                                        .orElseThrow());
                        charlottesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.WE,
                                                        daytimeRepository.findByName(DaytimeName.ABENDS))
                                        .orElseThrow());
                        charlottesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.TH,
                                                        daytimeRepository.findByName(DaytimeName.MORGENS))
                                        .orElseThrow());
                        charlottesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.TH,
                                                        daytimeRepository.findByName(DaytimeName.MITTAGS))
                                        .orElseThrow());
                        charlottesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.FR,
                                                        daytimeRepository.findByName(DaytimeName.MORGENS))
                                        .orElseThrow());
                        charlottesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.FR,
                                                        daytimeRepository.findByName(DaytimeName.MITTAGS))
                                        .orElseThrow());
                        charlottesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.SA,
                                                        daytimeRepository.findByName(DaytimeName.MORGENS))
                                        .orElseThrow());
                        charlottesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.SU,
                                                        daytimeRepository.findByName(DaytimeName.MORGENS))
                                        .orElseThrow());
                        charlottesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.SU,
                                                        daytimeRepository.findByName(DaytimeName.MITTAGS))
                                        .orElseThrow());
                        charlottesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.SU,
                                                        daytimeRepository.findByName(DaytimeName.ABENDS))
                                        .orElseThrow());
                        charlotte.setSlots(charlottesSlots);

                        List<Course> charlottesKurse = new ArrayList<>();

                        charlottesKurse.add(courseRepository.findByName("Hantel Workout"));

                        charlotte.setCourses(charlottesKurse);

                        userRepository.save(charlotte);

                        User zoe = userRepository.findByName("zoe.richter");

                        List<Slot> zoesSlots = new ArrayList<>();

                        zoesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.MO,
                                                        daytimeRepository.findByName(DaytimeName.MORGENS))
                                        .orElseThrow());
                        zoesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.MO,
                                                        daytimeRepository.findByName(DaytimeName.MITTAGS))
                                        .orElseThrow());
                        zoesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.MO,
                                                        daytimeRepository.findByName(DaytimeName.ABENDS))
                                        .orElseThrow());
                        zoesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.WE,
                                                        daytimeRepository.findByName(DaytimeName.MITTAGS))
                                        .orElseThrow());
                        zoesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.TH,
                                                        daytimeRepository.findByName(DaytimeName.MORGENS))
                                        .orElseThrow());
                        zoesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.TH,
                                                        daytimeRepository.findByName(DaytimeName.MITTAGS))
                                        .orElseThrow());
                        zoesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.TH,
                                                        daytimeRepository.findByName(DaytimeName.ABENDS))
                                        .orElseThrow());
                        zoesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.FR,
                                                        daytimeRepository.findByName(DaytimeName.MORGENS))
                                        .orElseThrow());
                        zoesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.FR,
                                                        daytimeRepository.findByName(DaytimeName.MITTAGS))
                                        .orElseThrow());
                        zoesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.FR,
                                                        daytimeRepository.findByName(DaytimeName.ABENDS))
                                        .orElseThrow());
                        zoesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.SU,
                                                        daytimeRepository.findByName(DaytimeName.MORGENS))
                                        .orElseThrow());
                        zoesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.SU,
                                                        daytimeRepository.findByName(DaytimeName.MITTAGS))
                                        .orElseThrow());
                        zoesSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.SU,
                                                        daytimeRepository.findByName(DaytimeName.ABENDS))
                                        .orElseThrow());
                        zoe.setSlots(zoesSlots);

                        List<Course> zoesKurse = new ArrayList<>();

                        zoesKurse.add(courseRepository.findByName("Hantel Workout"));

                        zoe.setCourses(zoesKurse);

                        userRepository.save(zoe);

                        User max = userRepository.findByName("max.meier");

                        List<Slot> maxensSlots = new ArrayList<>();
                        maxensSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.MO,
                                                        daytimeRepository.findByName(DaytimeName.MITTAGS))
                                        .orElseThrow());
                        maxensSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.MO,
                                                        daytimeRepository.findByName(DaytimeName.ABENDS))
                                        .orElseThrow());
                        maxensSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.TU,
                                                        daytimeRepository.findByName(DaytimeName.MITTAGS))
                                        .orElseThrow());
                        maxensSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.TU,
                                                        daytimeRepository.findByName(DaytimeName.ABENDS))
                                        .orElseThrow());
                        maxensSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.WE,
                                                        daytimeRepository.findByName(DaytimeName.MITTAGS))
                                        .orElseThrow());
                        maxensSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.WE,
                                                        daytimeRepository.findByName(DaytimeName.ABENDS))
                                        .orElseThrow());
                        maxensSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.SA,
                                                        daytimeRepository.findByName(DaytimeName.MORGENS))
                                        .orElseThrow());
                        maxensSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.SA,
                                                        daytimeRepository.findByName(DaytimeName.MITTAGS))
                                        .orElseThrow());
                        maxensSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.SA,
                                                        daytimeRepository.findByName(DaytimeName.ABENDS))
                                        .orElseThrow());
                        maxensSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.SU,
                                                        daytimeRepository.findByName(DaytimeName.MORGENS))
                                        .orElseThrow());
                        maxensSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.SU,
                                                        daytimeRepository.findByName(DaytimeName.MITTAGS))
                                        .orElseThrow());
                        maxensSlots.add(slotRepository
                                        .findByWeekdayAndDaytime(Weekday.SU,
                                                        daytimeRepository.findByName(DaytimeName.ABENDS))
                                        .orElseThrow());
                        max.setSlots(maxensSlots);

                        List<Course> maxensKurse = new ArrayList<>();

                        maxensKurse.add(courseRepository.findByName("Hantel Workout"));

                        max.setCourses(maxensKurse);

                        userRepository.save(max);

                }

                // KURSE
                if (courseRepository.count() == 0) {
                        courseService.Seed("Jumping Fit",
                                        "Beim Jumping Fitness Workout sind über 400 Muskeln im Einsatz, viel mehr als bei vergleichbaren Ausdauersportarten. Jumping Fitness ist deutlich effektiver als Joggen und schont gleichzeitig die Gelenke."
                                                        + " Was das Ganzkörpertraining Jumping Fitness jedoch so besonders macht, ist das Spiel mit der Schwerkraft. Auch wenn es einem nicht so vorkommt: Der Moment des Abhebens ist für die Muskulatur Schwerstarbeit."
                                                        + " Sie muss ein Vielfaches des eigentlichen Körpergewichts stemmen. Diese Anstrengung hat aber einen großen Vorteil: Die Muskulatur und auch die Knochen werden gestärkt.",
                                        Category.BODYWEIGHT, TargetGroup.WEIGHTLOSS, 60);

                        courseService.Seed("Hantel Workout",
                                        "Beim Hanteltraining werden hauptsächlich Grundübungen von Isolationsübungen unterschieden."
                                                        + " Diese haben unterschiedliche Auswirkungen auf die beabsichtigten Zielstellungen beim Hanteltraining. Die Zielstellungen beim Hanteltraining reichen von Muskelaufbau,"
                                                        + " Kraftzuwachs bis hin zum Abnehmen und umfassen auch präventive und rehabilitative Maßnahmen zur Gesundheitsförderung. Für viele Hantelübungen ist die korrekte Technik"
                                                        + " von großer Bedeutung, da es sonst zu gesundheitlichen Folgeschäden kommen kann. Lassen Sie sich also von unseren gut ausgebildeten Trainern die korrekte Technik zeigen.",
                                        Category.WEIGHTS, TargetGroup.MUSCLES, 60);

                        courseService.Seed("Krankengymnastik am Gerät",
                                        "Die Krankengymnastik am Gerät ist eine individuelle medizinische Trainingstherapie. Sie können mit Hilfe der gerätegestützten Krankengymnastik Ihre Muskulatur aufbauen und Ihren Körper ins Gleichgewicht bringen."
                                                        + " Außerdem ergänzt die Krankengymnastik am Gerät als medizinische Trainingstherapie die Einzeltherapie ideal bei fast allen orthopädischen, chirurgischen und selbst bei neurologischen Beschwerden."
                                                        + "Die Krankengymnastik am Gerät wird individuell nach Ihren Bedürfnissen entweder als Aufbautraining oder als Therapie eingesetzt.",
                                        Category.WEIGHTS, TargetGroup.REHA, 60);

                        courseService.Seed("Aqua Jogging",
                                        "Beim Aqua Jogging werden verschiedene Laufarten im Tiefwasser trainiert. Dazu wird mit Hilfe von Auftriebsgürteln ein Schwebezustand ermöglicht."
                                                        + " Gymnastik, kombiniert mit Laufbewegungen, ist ebenfalls Inhalt dieses Angebotes. Aqua Jogging ist für gesundheitsorientierte Menschen, insbesondere diejenigen mit Bewegungseinschränkungen des Hüft-,"
                                                        + " Knie- und Fußgelenkes, aber auch für Fitnessorientierte geeignet. Diese Wassersportart ist generell für Personen jeden Alters geeignet und sogar als Therapie anerkannt.",
                                        Category.AQUA, TargetGroup.REHA, 60);

                        courseService.Seed("Aqua Kick Punch",
                                        "Aqua Kick Punch ist eine Kombination aus Kampfsportelementen des traditionellen Taekwondo und des Boxens im stehtiefen Wasser."
                                                        + " Das effektive Training macht viel Spaß und stärkt zudem das Selbstvertrauen der Teilnehmer/-innen jeden Alters. Im Takt mitreißender Musik wird gegen den Wasserwiderstand geboxt, getreten und gekickt."
                                                        + " Dabei ist Aqua Kick Punch durch den unterstützenden Auftrieb des Wassers ein gelenkschonendes und muskelkräftigendes Training. Stoßbelastungen auf Knochen und Gelenke sind fast völlig auszuschließen.",
                                        Category.AQUA, TargetGroup.RELAXED, 60);

                        courseService.Seed("Aqua Cycling",
                                        "Aqua Cycling – das Radfahren im Wasser – verbindet die Prinzipien des klassischen Spinning-Kurses aus dem Fitnessstudio mit der gesunden und angenehmen Wirkung des Wassers."
                                                        + " Auf speziell entwickelten Aquabikes werden zum Rhythmus der Musik Gesäß- und Bein- sowie Armmuskulatur durch effektives Ausdauertraining gekräftigt. Aqua Cycling ist relativ einfach und schnell zu erlernen."
                                                        + " Diese Wassersportart ist generell für Personen jeden Alters geeignet und sogar als Therapie anerkannt.",
                                        Category.AQUA, TargetGroup.REHA, 60);

                        courseService.Seed("Zumba",
                                        "Für alle und jeden! Jeder Zumba Kurs soll Leute zusammenbringen, die dann gemeinsam trainieren und Spaß haben. Wir kombinieren Bewegungen mit niedriger und hoher Intensität zu einem kalorienverbrennenden Intervalltraining,"
                                                        + " bei dem man sich wie auf einer Tanz-Fitness-Party fühlt. Wenn dich die lateinamerikanischen und weltweiten Rhythmen erst einmal gepackt haben, wirst du verstehen, warum Zumba Fitness Kurse oft gar nicht als Workout wahrgenommen werden."
                                                        + " Jedes Mal, wenn du aus dem Kurs kommst, sprühst du nur so vor Energie und fühlst dich einfach toll",
                                        Category.DANCE, TargetGroup.WEIGHTLOSS, 60);

                        courseService.Seed("NIA",
                                        "NIA ist ein ganzheitliches Fitnesskonzept aus den USA, das Körper, Geist und Seele berührt und in Einklang bringt. Elemente aus Tanz, Yoga, asiatischen Kampfsportarten wie Taekwondo und Aikido sind durch Musik miteinander verbunden."
                                                        + " Fließende und natürliche Bewegungen schonen die Gelenke und trainieren Balance, Beweglichkeit, Elastizität und Kraft des Körpers - mal zackig, mal in Zeitlupe. Alter und Kondition spielen keine Rolle."
                                                        + " Du verbrennst in einer Stunde Nia viele Kalorien, ohne dabei überhaupt zu merken, dass Du in einem fordernden Training bist. Nia gibt dir Energie und hilft Dir, auch im Alltag bei Stress locker und entspannt zu bleiben.",
                                        Category.DANCE, TargetGroup.WEIGHTLOSS, 60);

                        courseService.Seed("BOKWA",
                                        "Bokwa ist eine Mischung aus Kick-Boxen und Tanzen. Der Name setzt sich aus Boxen und Kwaito, einer afrikanischen Musikrichtung, zusammen. Feste Choreograpfien und kompliziertes Zählen gibt es beim Bokwa nicht."
                                                        + " Stattdessen soll man sich einfach in die Musik hinein fühlen und sich frei mit dem Rhythmus bewegen. Die Schritte selbst sind ziemlich einfach und schnell erlernbar. Dadurch, dass sie auf Zahlen oder Buchstaben basieren,"
                                                        + " kann man sie relativ einfach gedanklich abrufen und abschreiten. Das Erfolgsrezept: Bokwa ist einfach und schweißtreibend. Der ganze Körper wird trainiert und Sie tanzen den Kalorien davon!",
                                        Category.DANCE, TargetGroup.WEIGHTLOSS, 60);

                        courseService.Seed("Bodyweight kompakt",
                                        "Mit Bodyweight-Übungen wie Klimmzüge, Kniebeugen und Liegestütz forderst du alle Muskeln deines Körpers. Du kräftigst mehrere Muskeln gleichzeitig und trainierst ihr Zusammenspiel."
                                                        + " So baust du Fett ab und definierst deine Muskeln. Gleichzeitig verbesserst du mit jedem Bodyweight-Training deine Koordination und forderst deine Stabilität. Dein Core ist immer im Einsatz."
                                                        + " Das Resultat: Mit jedem Workout stärkst du deine Rumpfmuskulatur und es fällt dir leichter, dein Gleichgewicht zu halten. Das Bodyweight Kompakttraining kombiniert viele Elemente.",
                                        Category.BODYWEIGHT, TargetGroup.MUSCLES, 60);

                }
                // TERMINE
                if (eventRepository.count() == 0) {
                        eventService.Seed(LocalTime.of(8, 30), courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("zoe.richter"), Room.GYM1, Weekday.MO);

                        eventService.Seed(LocalTime.of(12, 30), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("zoe.richter"), Room.GYM1, Weekday.MO);

                        eventService.Seed(LocalTime.of(14, 00), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("zoe.richter"), Room.GYM1, Weekday.MO);

                        eventService.Seed(LocalTime.of(16, 30), courseRepository.findByName("Bodyweight kompakt"),
                                        userRepository.findByName("zoe.richter"), Room.CROSS, Weekday.MO);

                        eventService.Seed(LocalTime.of(9, 30), courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("zoe.richter"), Room.GYM1, Weekday.TU);

                        eventService.Seed(LocalTime.of(12, 30), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("zoe.richter"), Room.GYM1, Weekday.TU);

                        eventService.Seed(LocalTime.of(15, 00), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("zoe.richter"), Room.GYM1, Weekday.TU);

                        eventService.Seed(LocalTime.of(18, 30), courseRepository.findByName("Bodyweight kompakt"),
                                        userRepository.findByName("zoe.richter"), Room.CROSS, Weekday.TU);

                        eventService.Seed(LocalTime.of(10, 00),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("zoe.richter"), Room.GYM1, Weekday.WE);

                        eventService.Seed(LocalTime.of(13, 00), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("zoe.richter"), Room.GYM1, Weekday.WE);

                        eventService.Seed(LocalTime.of(15, 00), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("zoe.richter"), Room.GYM1, Weekday.WE);

                        eventService.Seed(LocalTime.of(18, 30), courseRepository.findByName("Bodyweight kompakt"),
                                        userRepository.findByName("zoe.richter"), Room.CROSS, Weekday.WE);

                        eventService.Seed(LocalTime.of(8, 30), courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("zoe.richter"), Room.GYM1, Weekday.TH);

                        eventService.Seed(LocalTime.of(12, 30), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("zoe.richter"), Room.GYM1, Weekday.TH);

                        eventService.Seed(LocalTime.of(14, 30), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("zoe.richter"), Room.GYM1, Weekday.TH);

                        eventService.Seed(LocalTime.of(18, 30), courseRepository.findByName("Bodyweight kompakt"),
                                        userRepository.findByName("zoe.richter"), Room.CROSS, Weekday.TH);

                        eventService.Seed(LocalTime.of(9, 30), courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("zoe.richter"), Room.GYM1, Weekday.FR);

                        eventService.Seed(LocalTime.of(13, 00), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("zoe.richter"), Room.GYM1, Weekday.FR);

                        eventService.Seed(LocalTime.of(15, 00), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("zoe.richter"), Room.GYM1, Weekday.FR);

                        eventService.Seed(LocalTime.of(17, 00), courseRepository.findByName("Bodyweight kompakt"),
                                        userRepository.findByName("zoe.richter"), Room.CROSS, Weekday.FR);

                        eventService.Seed(LocalTime.of(8, 00), courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("zoe.richter"), Room.GYM1, Weekday.SA);

                        eventService.Seed(LocalTime.of(12, 30), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("zoe.richter"), Room.GYM1, Weekday.SA);

                        eventService.Seed(LocalTime.of(15, 00), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("zoe.richter"), Room.GYM1, Weekday.SA);

                        eventService.Seed(LocalTime.of(18, 30), courseRepository.findByName("Bodyweight kompakt"),
                                        userRepository.findByName("zoe.richter"), Room.CROSS, Weekday.SA);

                        eventService.Seed(LocalTime.of(10, 00),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("zoe.richter"), Room.GYM1, Weekday.SU);

                        eventService.Seed(LocalTime.of(12, 30), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("zoe.richter"), Room.GYM1, Weekday.SU);

                        eventService.Seed(LocalTime.of(14, 00), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("zoe.richter"), Room.GYM1, Weekday.SU);

                        eventService.Seed(LocalTime.of(17, 00), courseRepository.findByName("Bodyweight kompakt"),
                                        userRepository.findByName("zoe.richter"), Room.CROSS, Weekday.SU);

                        eventService.Seed(LocalTime.of(9, 30), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("anne.baum"), Room.GYM1, Weekday.MO);

                        eventService.Seed(LocalTime.of(13, 30),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("anne.baum"), Room.GYM1, Weekday.MO);

                        eventService.Seed(LocalTime.of(15, 00), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("anne.baum"), Room.GYM1, Weekday.MO);

                        eventService.Seed(LocalTime.of(17, 30), courseRepository.findByName("Zumba"),
                                        userRepository.findByName("anne.baum"), Room.DANCING, Weekday.MO);

                        eventService.Seed(LocalTime.of(10, 30), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("anne.baum"), Room.GYM1, Weekday.TU);

                        eventService.Seed(LocalTime.of(13, 30),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("anne.baum"), Room.GYM1, Weekday.TU);

                        eventService.Seed(LocalTime.of(16, 00), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("anne.baum"), Room.GYM1, Weekday.TU);

                        eventService.Seed(LocalTime.of(19, 30), courseRepository.findByName("Zumba"),
                                        userRepository.findByName("anne.baum"), Room.DANCING, Weekday.TU);

                        eventService.Seed(LocalTime.of(11, 00), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("anne.baum"), Room.GYM1, Weekday.WE);

                        eventService.Seed(LocalTime.of(14, 00),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("anne.baum"), Room.GYM1, Weekday.WE);

                        eventService.Seed(LocalTime.of(16, 00), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("anne.baum"), Room.GYM1, Weekday.WE);

                        eventService.Seed(LocalTime.of(19, 30), courseRepository.findByName("Zumba"),
                                        userRepository.findByName("anne.baum"), Room.DANCING, Weekday.WE);

                        eventService.Seed(LocalTime.of(9, 30), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("anne.baum"), Room.GYM1, Weekday.TH);

                        eventService.Seed(LocalTime.of(13, 30),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("anne.baum"), Room.GYM1, Weekday.TH);

                        eventService.Seed(LocalTime.of(15, 30), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("anne.baum"), Room.GYM1, Weekday.TH);

                        eventService.Seed(LocalTime.of(19, 30), courseRepository.findByName("Zumba"),
                                        userRepository.findByName("anne.baum"), Room.DANCING, Weekday.TH);

                        eventService.Seed(LocalTime.of(10, 30), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("anne.baum"), Room.GYM1, Weekday.FR);

                        eventService.Seed(LocalTime.of(14, 00),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("anne.baum"), Room.GYM1, Weekday.FR);

                        eventService.Seed(LocalTime.of(16, 00), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("anne.baum"), Room.GYM1, Weekday.FR);

                        eventService.Seed(LocalTime.of(18, 00), courseRepository.findByName("Zumba"),
                                        userRepository.findByName("anne.baum"), Room.DANCING, Weekday.FR);

                        eventService.Seed(LocalTime.of(9, 00), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("anne.baum"), Room.GYM1, Weekday.SA);

                        eventService.Seed(LocalTime.of(13, 30),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("anne.baum"), Room.GYM1, Weekday.SA);

                        eventService.Seed(LocalTime.of(14, 00), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("anne.baum"), Room.GYM1, Weekday.SA);

                        eventService.Seed(LocalTime.of(19, 30), courseRepository.findByName("Zumba"),
                                        userRepository.findByName("anne.baum"), Room.DANCING, Weekday.SA);

                        eventService.Seed(LocalTime.of(11, 00), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("anne.baum"), Room.GYM1, Weekday.SU);

                        eventService.Seed(LocalTime.of(13, 30),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("anne.baum"), Room.GYM1, Weekday.SU);

                        eventService.Seed(LocalTime.of(15, 00), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("anne.baum"), Room.GYM1, Weekday.SU);

                        eventService.Seed(LocalTime.of(18, 00), courseRepository.findByName("Zumba"),
                                        userRepository.findByName("anne.baum"), Room.DANCING, Weekday.SU);

                        eventService.Seed(LocalTime.of(9, 30), courseRepository.findByName("Aqua Cycling"),
                                        userRepository.findByName("charlotte.tulpe"), Room.SWIMMING, Weekday.MO);

                        eventService.Seed(LocalTime.of(13, 30), courseRepository.findByName("Aqua Kick Punch"),
                                        userRepository.findByName("charlotte.tulpe"), Room.SWIMMING, Weekday.MO);

                        eventService.Seed(LocalTime.of(15, 00), courseRepository.findByName("Aqua Jogging"),
                                        userRepository.findByName("charlotte.tulpe"), Room.SWIMMING, Weekday.MO);

                        eventService.Seed(LocalTime.of(17, 30), courseRepository.findByName("BOKWA"),
                                        userRepository.findByName("charlotte.tulpe"), Room.DANCING, Weekday.MO);

                        eventService.Seed(LocalTime.of(10, 30), courseRepository.findByName("Aqua Cycling"),
                                        userRepository.findByName("charlotte.tulpe"), Room.SWIMMING, Weekday.TU);

                        eventService.Seed(LocalTime.of(13, 30), courseRepository.findByName("Aqua Kick Punch"),
                                        userRepository.findByName("charlotte.tulpe"), Room.SWIMMING, Weekday.TU);

                        eventService.Seed(LocalTime.of(16, 00), courseRepository.findByName("Aqua Jogging"),
                                        userRepository.findByName("charlotte.tulpe"), Room.SWIMMING, Weekday.TU);

                        eventService.Seed(LocalTime.of(19, 30), courseRepository.findByName("BOKWA"),
                                        userRepository.findByName("charlotte.tulpe"), Room.DANCING, Weekday.TU);

                        eventService.Seed(LocalTime.of(11, 00), courseRepository.findByName("Aqua Cycling"),
                                        userRepository.findByName("charlotte.tulpe"), Room.SWIMMING, Weekday.WE);

                        eventService.Seed(LocalTime.of(14, 00), courseRepository.findByName("Aqua Kick Punch"),
                                        userRepository.findByName("charlotte.tulpe"), Room.SWIMMING, Weekday.WE);

                        eventService.Seed(LocalTime.of(16, 00), courseRepository.findByName("Aqua Jogging"),
                                        userRepository.findByName("charlotte.tulpe"), Room.SWIMMING, Weekday.WE);

                        eventService.Seed(LocalTime.of(19, 30), courseRepository.findByName("BOKWA"),
                                        userRepository.findByName("charlotte.tulpe"), Room.DANCING, Weekday.WE);

                        eventService.Seed(LocalTime.of(9, 30), courseRepository.findByName("Aqua Cycling"),
                                        userRepository.findByName("charlotte.tulpe"), Room.SWIMMING, Weekday.TH);

                        eventService.Seed(LocalTime.of(13, 30), courseRepository.findByName("Aqua Kick Punch"),
                                        userRepository.findByName("charlotte.tulpe"), Room.SWIMMING, Weekday.TH);

                        eventService.Seed(LocalTime.of(15, 30), courseRepository.findByName("Aqua Jogging"),
                                        userRepository.findByName("charlotte.tulpe"), Room.SWIMMING, Weekday.TH);

                        eventService.Seed(LocalTime.of(19, 30), courseRepository.findByName("BOKWA"),
                                        userRepository.findByName("charlotte.tulpe"), Room.DANCING, Weekday.TH);

                        eventService.Seed(LocalTime.of(10, 30), courseRepository.findByName("Aqua Cycling"),
                                        userRepository.findByName("charlotte.tulpe"), Room.SWIMMING, Weekday.FR);

                        eventService.Seed(LocalTime.of(14, 00), courseRepository.findByName("Aqua Kick Punch"),
                                        userRepository.findByName("charlotte.tulpe"), Room.SWIMMING, Weekday.FR);

                        eventService.Seed(LocalTime.of(16, 00), courseRepository.findByName("Aqua Jogging"),
                                        userRepository.findByName("charlotte.tulpe"), Room.SWIMMING, Weekday.FR);

                        eventService.Seed(LocalTime.of(18, 00), courseRepository.findByName("BOKWA"),
                                        userRepository.findByName("charlotte.tulpe"), Room.DANCING, Weekday.FR);

                        eventService.Seed(LocalTime.of(9, 00), courseRepository.findByName("Aqua Cycling"),
                                        userRepository.findByName("charlotte.tulpe"), Room.SWIMMING, Weekday.SA);

                        eventService.Seed(LocalTime.of(13, 30), courseRepository.findByName("Aqua Kick Punch"),
                                        userRepository.findByName("charlotte.tulpe"), Room.SWIMMING, Weekday.SA);

                        eventService.Seed(LocalTime.of(14, 00), courseRepository.findByName("Aqua Jogging"),
                                        userRepository.findByName("charlotte.tulpe"), Room.SWIMMING, Weekday.SA);

                        eventService.Seed(LocalTime.of(19, 30), courseRepository.findByName("BOKWA"),
                                        userRepository.findByName("charlotte.tulpe"), Room.DANCING, Weekday.SA);

                        eventService.Seed(LocalTime.of(11, 00), courseRepository.findByName("Aqua Cycling"),
                                        userRepository.findByName("charlotte.tulpe"), Room.SWIMMING, Weekday.SU);

                        eventService.Seed(LocalTime.of(13, 30), courseRepository.findByName("Aqua Kick Punch"),
                                        userRepository.findByName("charlotte.tulpe"), Room.SWIMMING, Weekday.SU);

                        eventService.Seed(LocalTime.of(15, 00), courseRepository.findByName("Aqua Jogging"),
                                        userRepository.findByName("charlotte.tulpe"), Room.SWIMMING, Weekday.SU);

                        eventService.Seed(LocalTime.of(18, 00), courseRepository.findByName("BOKWA"),
                                        userRepository.findByName("charlotte.tulpe"), Room.DANCING, Weekday.SU);

                        eventService.Seed(LocalTime.of(9, 30), courseRepository.findByName("NIA"),
                                        userRepository.findByName("charlotte.tulpe"), Room.DANCING, Weekday.MO);

                        eventService.Seed(LocalTime.of(13, 30), courseRepository.findByName("Zumba"),
                                        userRepository.findByName("charlotte.tulpe"), Room.DANCING, Weekday.MO);

                        eventService.Seed(LocalTime.of(15, 00), courseRepository.findByName("BOKWA"),
                                        userRepository.findByName("charlotte.tulpe"), Room.DANCING, Weekday.MO);

                        eventService.Seed(LocalTime.of(17, 30), courseRepository.findByName("Aqua Kick Punch"),
                                        userRepository.findByName("charlotte.tulpe"), Room.SWIMMING, Weekday.MO);

                        eventService.Seed(LocalTime.of(10, 30), courseRepository.findByName("NIA"),
                                        userRepository.findByName("charlotte.tulpe"), Room.DANCING, Weekday.TU);

                        eventService.Seed(LocalTime.of(13, 30), courseRepository.findByName("Zumba"),
                                        userRepository.findByName("charlotte.tulpe"), Room.DANCING, Weekday.TU);

                        eventService.Seed(LocalTime.of(16, 00), courseRepository.findByName("BOKWA"),
                                        userRepository.findByName("charlotte.tulpe"), Room.DANCING, Weekday.TU);

                        eventService.Seed(LocalTime.of(19, 30), courseRepository.findByName("Aqua Kick Punch"),
                                        userRepository.findByName("charlotte.tulpe"), Room.SWIMMING, Weekday.TU);

                        eventService.Seed(LocalTime.of(11, 00), courseRepository.findByName("NIA"),
                                        userRepository.findByName("charlotte.tulpe"), Room.DANCING, Weekday.WE);

                        eventService.Seed(LocalTime.of(14, 00), courseRepository.findByName("Zumba"),
                                        userRepository.findByName("charlotte.tulpe"), Room.DANCING, Weekday.WE);

                        eventService.Seed(LocalTime.of(16, 00), courseRepository.findByName("BOKWA"),
                                        userRepository.findByName("charlotte.tulpe"), Room.DANCING, Weekday.WE);

                        eventService.Seed(LocalTime.of(19, 30), courseRepository.findByName("Aqua Kick Punch"),
                                        userRepository.findByName("charlotte.tulpe"), Room.SWIMMING, Weekday.WE);

                        eventService.Seed(LocalTime.of(9, 30), courseRepository.findByName("NIA"),
                                        userRepository.findByName("charlotte.tulpe"), Room.DANCING, Weekday.TH);

                        eventService.Seed(LocalTime.of(13, 30), courseRepository.findByName("Zumba"),
                                        userRepository.findByName("charlotte.tulpe"), Room.DANCING, Weekday.TH);

                        eventService.Seed(LocalTime.of(15, 30), courseRepository.findByName("BOKWA"),
                                        userRepository.findByName("charlotte.tulpe"), Room.DANCING, Weekday.TH);

                        eventService.Seed(LocalTime.of(19, 30), courseRepository.findByName("Aqua Kick Punch"),
                                        userRepository.findByName("charlotte.tulpe"), Room.SWIMMING, Weekday.TH);

                        eventService.Seed(LocalTime.of(10, 30), courseRepository.findByName("NIA"),
                                        userRepository.findByName("charlotte.tulpe"), Room.DANCING, Weekday.FR);

                        eventService.Seed(LocalTime.of(14, 00), courseRepository.findByName("Zumba"),
                                        userRepository.findByName("charlotte.tulpe"), Room.DANCING, Weekday.FR);

                        eventService.Seed(LocalTime.of(16, 00), courseRepository.findByName("BOKWA"),
                                        userRepository.findByName("charlotte.tulpe"), Room.DANCING, Weekday.FR);

                        eventService.Seed(LocalTime.of(18, 00), courseRepository.findByName("Aqua Kick Punch"),
                                        userRepository.findByName("charlotte.tulpe"), Room.SWIMMING, Weekday.FR);

                        eventService.Seed(LocalTime.of(9, 00), courseRepository.findByName("NIA"),
                                        userRepository.findByName("charlotte.tulpe"), Room.DANCING, Weekday.SA);

                        eventService.Seed(LocalTime.of(13, 30), courseRepository.findByName("Zumba"),
                                        userRepository.findByName("charlotte.tulpe"), Room.DANCING, Weekday.SA);

                        eventService.Seed(LocalTime.of(14, 00), courseRepository.findByName("BOKWA"),
                                        userRepository.findByName("charlotte.tulpe"), Room.DANCING, Weekday.SA);

                        eventService.Seed(LocalTime.of(19, 30), courseRepository.findByName("Aqua Kick Punch"),
                                        userRepository.findByName("charlotte.tulpe"), Room.SWIMMING, Weekday.SA);

                        eventService.Seed(LocalTime.of(11, 00), courseRepository.findByName("NIA"),
                                        userRepository.findByName("charlotte.tulpe"), Room.DANCING, Weekday.SU);

                        eventService.Seed(LocalTime.of(13, 30), courseRepository.findByName("Zumba"),
                                        userRepository.findByName("charlotte.tulpe"), Room.DANCING, Weekday.SU);

                        eventService.Seed(LocalTime.of(15, 00), courseRepository.findByName("BOKWA"),
                                        userRepository.findByName("charlotte.tulpe"), Room.DANCING, Weekday.SU);

                        eventService.Seed(LocalTime.of(18, 00), courseRepository.findByName("Aqua Kick Punch"),
                                        userRepository.findByName("charlotte.tulpe"), Room.SWIMMING, Weekday.SU);

                        eventService.Seed(LocalTime.of(8, 30), courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("benedikt.müller"), Room.GYM2, Weekday.MO);

                        eventService.Seed(LocalTime.of(12, 30), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("benedikt.müller"), Room.CROSS, Weekday.MO);

                        eventService.Seed(LocalTime.of(14, 00),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("benedikt.müller"), Room.GYM2, Weekday.MO);

                        eventService.Seed(LocalTime.of(16, 30), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("benedikt.müller"), Room.GYM1, Weekday.MO);

                        eventService.Seed(LocalTime.of(9, 30), courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("benedikt.müller"), Room.GYM2, Weekday.TU);

                        eventService.Seed(LocalTime.of(12, 30), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("benedikt.müller"), Room.CROSS, Weekday.TU);

                        eventService.Seed(LocalTime.of(15, 00),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("benedikt.müller"), Room.GYM2, Weekday.TU);

                        eventService.Seed(LocalTime.of(18, 30), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("benedikt.müller"), Room.GYM1, Weekday.TU);

                        eventService.Seed(LocalTime.of(10, 00),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("benedikt.müller"), Room.GYM2, Weekday.WE);

                        eventService.Seed(LocalTime.of(13, 00), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("benedikt.müller"), Room.CROSS, Weekday.WE);

                        eventService.Seed(LocalTime.of(15, 00),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("benedikt.müller"), Room.GYM2, Weekday.WE);

                        eventService.Seed(LocalTime.of(18, 30), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("benedikt.müller"), Room.GYM1, Weekday.WE);

                        eventService.Seed(LocalTime.of(8, 30), courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("benedikt.müller"), Room.GYM2, Weekday.TH);

                        eventService.Seed(LocalTime.of(12, 30), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("benedikt.müller"), Room.CROSS, Weekday.TH);

                        eventService.Seed(LocalTime.of(14, 30),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("benedikt.müller"), Room.GYM2, Weekday.TH);

                        eventService.Seed(LocalTime.of(18, 30), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("benedikt.müller"), Room.GYM1, Weekday.TH);

                        eventService.Seed(LocalTime.of(9, 30), courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("benedikt.müller"), Room.GYM2, Weekday.FR);

                        eventService.Seed(LocalTime.of(13, 00), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("benedikt.müller"), Room.CROSS, Weekday.FR);

                        eventService.Seed(LocalTime.of(15, 00),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("benedikt.müller"), Room.GYM2, Weekday.FR);

                        eventService.Seed(LocalTime.of(17, 00), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("benedikt.müller"), Room.GYM1, Weekday.FR);

                        eventService.Seed(LocalTime.of(8, 00), courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("benedikt.müller"), Room.GYM2, Weekday.SA);

                        eventService.Seed(LocalTime.of(12, 30), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("benedikt.müller"), Room.CROSS, Weekday.SA);

                        eventService.Seed(LocalTime.of(15, 00),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("benedikt.müller"), Room.GYM2, Weekday.SA);

                        eventService.Seed(LocalTime.of(18, 30), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("benedikt.müller"), Room.GYM1, Weekday.SA);

                        eventService.Seed(LocalTime.of(10, 00),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("benedikt.müller"), Room.GYM2, Weekday.SU);

                        eventService.Seed(LocalTime.of(12, 30), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("benedikt.müller"), Room.CROSS, Weekday.SU);

                        eventService.Seed(LocalTime.of(14, 00),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("benedikt.müller"), Room.GYM2, Weekday.SU);

                        eventService.Seed(LocalTime.of(17, 00), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("benedikt.müller"), Room.GYM1, Weekday.SU);
                        eventService.Seed(LocalTime.of(9, 30), courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("max.meier"), Room.GYM2, Weekday.MO);

                        eventService.Seed(LocalTime.of(13, 30), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("max.meier"), Room.CROSS, Weekday.MO);

                        eventService.Seed(LocalTime.of(15, 00),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("max.meier"), Room.GYM2, Weekday.MO);

                        eventService.Seed(LocalTime.of(17, 30), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("max.meier"), Room.GYM1, Weekday.MO);

                        eventService.Seed(LocalTime.of(10, 30),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("max.meier"), Room.GYM2, Weekday.TU);

                        eventService.Seed(LocalTime.of(13, 30), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("max.meier"), Room.CROSS, Weekday.TU);

                        eventService.Seed(LocalTime.of(16, 30),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("max.meier"), Room.GYM2, Weekday.TU);

                        eventService.Seed(LocalTime.of(19, 30), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("max.meier"), Room.GYM1, Weekday.TU);

                        eventService.Seed(LocalTime.of(11, 00),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("max.meier"), Room.GYM2, Weekday.WE);

                        eventService.Seed(LocalTime.of(14, 00), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("max.meier"), Room.CROSS, Weekday.WE);

                        eventService.Seed(LocalTime.of(16, 00),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("max.meier"), Room.GYM2, Weekday.WE);

                        eventService.Seed(LocalTime.of(19, 30), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("max.meier"), Room.GYM1, Weekday.WE);

                        eventService.Seed(LocalTime.of(9, 30), courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("max.meier"), Room.GYM2, Weekday.TH);

                        eventService.Seed(LocalTime.of(13, 30), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("max.meier"), Room.CROSS, Weekday.TH);

                        eventService.Seed(LocalTime.of(15, 30),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("max.meier"), Room.GYM2, Weekday.TH);

                        eventService.Seed(LocalTime.of(19, 30), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("max.meier"), Room.GYM1, Weekday.TH);

                        eventService.Seed(LocalTime.of(10, 30),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("max.meier"), Room.GYM2, Weekday.FR);

                        eventService.Seed(LocalTime.of(14, 00), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("max.meier"), Room.CROSS, Weekday.FR);

                        eventService.Seed(LocalTime.of(16, 00),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("max.meier"), Room.GYM2, Weekday.FR);

                        eventService.Seed(LocalTime.of(18, 00), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("max.meier"), Room.GYM1, Weekday.FR);

                        eventService.Seed(LocalTime.of(9, 00), courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("max.meier"), Room.GYM2, Weekday.SA);

                        eventService.Seed(LocalTime.of(13, 30), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("max.meier"), Room.CROSS, Weekday.SA);

                        eventService.Seed(LocalTime.of(16, 00),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("max.meier"), Room.GYM2, Weekday.SA);

                        eventService.Seed(LocalTime.of(19, 30), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("max.meier"), Room.GYM1, Weekday.SA);

                        eventService.Seed(LocalTime.of(11, 00),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("max.meier"), Room.GYM2, Weekday.SU);

                        eventService.Seed(LocalTime.of(13, 30), courseRepository.findByName("Jumping Fit"),
                                        userRepository.findByName("max.meier"), Room.CROSS, Weekday.SU);

                        eventService.Seed(LocalTime.of(15, 00),
                                        courseRepository.findByName("Krankengymnastik am Gerät"),
                                        userRepository.findByName("max.meier"), Room.GYM2, Weekday.SU);

                        eventService.Seed(LocalTime.of(18, 00), courseRepository.findByName("Hantel Workout"),
                                        userRepository.findByName("max.meier"), Room.GYM1, Weekday.SU);

                }
        }

        public List<Event> findAll() {
                return eventRepository.findAll();
        }

}