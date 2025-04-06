package com.major.TimeTable.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
    @Query(value = "SELECT user from User user where user.contactDetails.email=:email")
    User findByEmail(@Param("email") String email);
}
