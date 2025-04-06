package com.major.TimeTable.business;

import com.major.TimeTable.common.AddressDetails;
import com.major.TimeTable.common.ContactDetails;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Business {
    @Id
    private UUID id;
    private String name;
    @Embedded
    private AddressDetails address;
    @Embedded
    private ContactDetails contact;
    private Date createdTimeStamp;
    private Date updatedTimeStamp;

    public Business() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
            this.createdTimeStamp = new Date();
            this.updatedTimeStamp = this.createdTimeStamp;
        }
    }
}
