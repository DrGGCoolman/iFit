package de.hsba.ifit.web;

import org.springframework.stereotype.Component;

import de.hsba.ifit.course.Course;

@Component
public class FormAssembler {

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

}
