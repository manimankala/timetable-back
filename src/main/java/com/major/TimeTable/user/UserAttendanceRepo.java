package com.major.TimeTable.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserAttendanceRepo extends JpaRepository<UserAttendance, UUID> {
}
