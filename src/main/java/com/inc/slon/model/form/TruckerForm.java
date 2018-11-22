package com.inc.slon.model.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

public class TruckerForm {

    @NotEmpty
    @Pattern(regexp = "^([A-Za-z]{1,25})$")
    private String firstName;

    @NotEmpty
    @Pattern(regexp = "^([A-Za-z]{1,35})$")
    private String lastName;

    @NotEmpty
    @Pattern(regexp = "^([0-9]{1,9})$")
    private String personalNumber;

    @NotEmpty
    private String cityId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
}
