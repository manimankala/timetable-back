package com.major.TimeTable.user;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class UserAttendance {
    @Id
    private UUID userId;
    @ElementCollection
    private List<LocalDateTime> dateTime;
}
