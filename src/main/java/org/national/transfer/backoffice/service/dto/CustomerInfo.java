package org.national.transfer.backoffice.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerInfo {

    private String customerID;
    private String lastname;
    private String firstname;
    private String gender;
    private String idType;
    private String birthDate;
    private String occupation;
    private String residentialAddress;
    private String city;
    private String phoneNumber;
    private String nationality;
    private String country;
    private String issuingCountry;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String emailAddress;
}
