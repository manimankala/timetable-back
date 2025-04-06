package com.major.TimeTable.common;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Embeddable
public class ContactDetails {
    private String countryCode;
    private String phone;
    @NotNull
    private String email;
    private String website;

}