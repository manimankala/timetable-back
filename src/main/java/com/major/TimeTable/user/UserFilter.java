package com.major.TimeTable.user;

import com.major.TimeTable.common.Constants;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.UUID;

@Data
public class UserFilter
{
    private UUID userId;
    private UUID businessId;
    private Constants.Role role;
    private Constants.Class className;
    private Constants.Section section;
    private Constants.Semester semester;
    private Constants.Branch branch;
    private String name;
    private String subject;
    private String designation;
    private String department;
    private String sortOrder;
    private String sortBy;
    private Integer pageSize;
    @Positive
    private Integer pageNo;
}
