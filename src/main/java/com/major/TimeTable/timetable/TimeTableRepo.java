package com.major.TimeTable.timetable;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TimeTableRepo extends JpaRepository<TimeTable, UUID> {
}
