package com.major.TimeTable.timetable;

import com.major.TimeTable.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/timetable")
public class TimeTableController {
    private final TimeTableService timeTableService;
    @Autowired
    public TimeTableController(TimeTableService timeTableService) {
        this.timeTableService = timeTableService;
    }

    @PostMapping(value = "")
    public Response createTimeTable(@RequestBody TimeTable timeTable) {
        return new Response(timeTableService.createTimeTable(timeTable));
    }

    @PutMapping(value = "/filter")
    public Response filterTimeTable(@RequestBody TimeTableFilter filter) {
        return new Response(timeTableService.getFilterTimeTable(filter));
    }

    @PutMapping(value = "")
    public Response updateTimeTable(@RequestBody TimeTable timeTable) {
        return new Response(timeTableService.updateTimeTable(timeTable));
    }

    @DeleteMapping(value = "")
    public Response deleteTimeTable(@RequestParam UUID id) {
        return new Response(timeTableService.deleteTimeTable(id));
    }


}
