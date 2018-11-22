package com.inc.slon.model.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

public class TruckerEditForm {

    @Pattern(regexp = "^([A-Za-z]{1,25})$")
    private String firstName;


    @Pattern(regexp = "^([A-Za-z]{1,35})$")
    private String lastName;


    @Pattern(regexp = "^([0-9]{1,9})$")
    private String personalNumber;

    @Pattern(regexp = "^(0|[1-9]|[1-9][0-9]|1[0-6][0-9]|17[0-6])$")
    private String workHours;

    private String cityId;

    @NotEmpty
    private String truckerId;

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

    public String getTruckerId() {
        return truckerId;
    }

    public void setTruckerId(String truckerId) {
        this.truckerId = truckerId;
    }

    public String getWorkHours() {
        return workHours;
    }

    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }
}
