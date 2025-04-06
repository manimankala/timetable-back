package com.major.TimeTable.common;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class AddressDetails {

    private String street;
    private String city;
    private String state;
    private int zipCode;

}