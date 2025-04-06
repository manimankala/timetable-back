package com.major.TimeTable.common;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.time.LocalTime;
import java.util.UUID;

@Data
@Embeddable
public class TimeTableEntry {
    private LocalTime fromTime;
    private LocalTime toTime;
    private String subject;
    private UUID staffId;
    private String staffName;
    private boolean isAvailable;

  }