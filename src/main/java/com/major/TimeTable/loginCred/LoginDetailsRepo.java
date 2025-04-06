package com.major.TimeTable.loginCred;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoginDetailsRepo extends JpaRepository<LoginDetails, UUID> {
    @Query(value = "SELECT ld FROM LoginDetails ld WHERE ld.username=:username")
    LoginDetails findByUsername(@Param("username") String username);
}
