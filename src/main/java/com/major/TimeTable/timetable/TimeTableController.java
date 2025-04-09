package com.major.TimeTable.timetable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/timetable")
public class TimeTableController {
    private final TimeTableService timeTableService;
    @Autowired
    public TimeTableController(TimeTableService timeTableService) {
        this.timeTableService = timeTableService;
    }

    @PostMapping(value = "")
    public TimeTable createTimeTable(TimeTable timeTable) {
        return timeTableService.createTimeTable(timeTable);
    }


}
