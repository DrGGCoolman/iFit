package de.hsba.ifit.filter;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hsba.ifit.event.Event;
import de.hsba.ifit.event.EventService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FilterService {
    @Autowired
    private EventService eventService;

    public List<Event> getFilteredEvents(Filter filter) {

        List<Event> filteredResults = eventService.findAll();

        if (filter.getSelectedCourse() != null)
            filteredResults.retainAll(eventService.findByCourseId(filter.getSelectedCourse().getId()));

        if (filter.getSelectedDaytime() != null)
            filteredResults.retainAll(eventService.findBetweenTimewindow(filter.getSelectedDaytime().getStart(),
                    filter.getSelectedDaytime().getEnd()));

        if (filter.getSelectedUser() != null)
            filteredResults.retainAll(eventService.findByUserId(filter.getSelectedUser().getId()));

        if (filter.getSelectedWeekday() != null)
            filteredResults
                    .retainAll(eventService.findByWeekdayOrderByStartAtAscCourseAsc(filter.getSelectedWeekday()));

        return filteredResults;
    }
}