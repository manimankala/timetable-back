package com.major.TimeTable.timetable;

import com.major.TimeTable.common.Constants;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Data
@Entity
public class TimeTable {
    @Id
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
    @ElementCollection
    private List<TimeTableElem> elemList;
    private Date createdTimeStamp;
    private Date updatedTimeStamp;

    public TimeTable() {
        if (id == null) {
            this.id = UUID.randomUUID();
            this.createdTimeStamp = new Date();
            this.updatedTimeStamp = this.createdTimeStamp;
            this.elemList = new ArrayList<>();
        }
    }

}