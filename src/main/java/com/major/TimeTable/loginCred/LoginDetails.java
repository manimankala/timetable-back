package com.major.TimeTable.loginCred;

import com.major.TimeTable.common.Constants;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;
@Data
@Entity
public class LoginDetails {
    @Id
    private UUID userId;
    private String username;
    private String password;
    private Constants.Role role;
}
