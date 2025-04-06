package com.major.TimeTable.timetable;

import com.major.TimeTable.common.Constants;
import lombok.Data;

@Data
public class TimeTableFilter {
    private Constants.Branch branch;
    private Constants.Class className;
    private Constants.Section section;
    private Constants.Semester semester;
    private String subject;

}
