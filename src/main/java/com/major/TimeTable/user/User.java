package com.major.TimeTable.user;

import com.major.TimeTable.common.AddressDetails;
import com.major.TimeTable.common.Constants;
import com.major.TimeTable.common.ContactDetails;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity
@Data
public class User {
    @Id
    private UUID id;
    private String name;
    private String unqNo;//rollNo for everyone
    private UUID businessId;
    private String businessName;
    @Embedded
    private AddressDetails addressDetails;
    @Embedded
    private ContactDetails contactDetails;
    private String subject; //for teacher and admin
    private String designation;
    private String department;
    private Constants.Class className;
    private Constants.Section section;
    private Constants.Semester semester;
    private Constants.Branch branch;
    private Date createdTimeStamp;
    private Date updatedTimeStamp;
    private Constants.Role role;
    public User() {
        if (id == null) {
            this.id = UUID.randomUUID();
            this.createdTimeStamp = new Date();
            this.updatedTimeStamp = this.createdTimeStamp;
            this.contactDetails = new ContactDetails();
            this.addressDetails = new AddressDetails();
        }
    }
}
