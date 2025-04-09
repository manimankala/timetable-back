package com.major.TimeTable.timetable;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Data;

import java.time.LocalTime;

@Data
@Embeddable
public class TimeTableElem {
    private String subject;
    private String teacher;
    private LocalTime startTime;
    private String endTime;
}
