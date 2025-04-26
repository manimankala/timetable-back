package com.major.TimeTable.notification;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Notification {
    @Id
    private UUID notificationId;
    private UUID userId;
    private String message;
    private Date createdTimeStamp;
    private Date updatedTimeStamp;

}
