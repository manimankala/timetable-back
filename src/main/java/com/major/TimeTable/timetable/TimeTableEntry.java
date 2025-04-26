package com.major.TimeTable.timetable;

import jakarta.persistence.*;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Embeddable
public class TimeTableEntry {
    @Transient
    private String fromTimeInput;
    @Transient
    private String toTimeInput;
    private DayOfWeek day;
    private LocalTime fromTime;
    private LocalTime toTime;
    private String subject;
    private UUID staffId;
    private String staffName;
    private boolean isAvailable;
  }