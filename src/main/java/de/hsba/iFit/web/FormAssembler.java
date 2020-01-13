package de.hsba.ifit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import de.hsba.ifit.course.Course;
import de.hsba.ifit.event.Event;
import de.hsba.ifit.user.User;

@Component
public class FormAssembler {
    @Autowired
    private PasswordEncoder Encoder;

    CourseForm toForm(Course course) {
        CourseForm form = new CourseForm();
        form.setName(course.getName());
        form.setDescription(course.getDescription());
        form.setCategory(course.getCategory());
        form.setTargetGroup(course.getTargetGroup());
        form.setDuration(course.getDuration());
        return form;
    }

    Course update(Course course, CourseForm form) {
        course.setName(form.getName());
        course.setDescription(form.getDescription());
        course.setCategory(form.getCategory());
        course.setTargetGroup(form.getTargetGroup());
        course.setDuration(form.getDuration());
        return course;
    }

    TrainerForm toForm(User user) {
        TrainerForm form = new TrainerForm();
        form.setFirstname(user.getFirstname());
        form.setLastname(user.getLastname());
        form.setName(user.getName());
        form.setPassword(user.getPassword());
        form.setRole(user.getRole());

        return form;
    }

    User update(User user, TrainerForm form) {
        user.setFirstname(form.getFirstname());
        user.setLastname(form.getLastname());
        user.setName(form.getName());
        user.setPassword(Encoder.encode(form.getPassword()));
        user.setRole(form.getRole());
        user.setCourses(form.getCourses());
        user.setEvents(form.getEvents());

        return user;
    }

    EventForm toForm(Event event) {
        EventForm form = new EventForm();
        form.setStartAt(event.getStartAt());
        form.setRoom(event.getRoom());
        form.setWeekday(event.getWeekday());
        form.setCourse(event.getCourse());
        form.setUser(event.getUser());

        return form;
    }

    Event update(Event event, EventForm form) {
        event.setStartAt(form.getStartAt());
        event.setRoom(form.getRoom());
        event.setWeekday(form.getWeekday());
        event.setCourse(form.getCourse());
        event.setUser(form.getUser());
        return event;
    }

}
