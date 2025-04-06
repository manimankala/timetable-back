package com.major.TimeTable.timetable;

import com.major.TimeTable.common.Constants;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;
@Data
@Entity
public class TimeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private UUID businessId;
    private String businessName;
    private UUID createdById;
    private String createdBy;
    private Constants.Class className;
    private Constants.Section section;
    private Constants.Semester semester;
    private Constants.Branch branch;
    private Date createdTimeStamp;
    private Date updatedTimeStamp;

}