package com.inc.slon.model.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class TruckForm {

    @NotEmpty
    @Pattern(regexp = "^([A-Za-z]{2}[0-9]{5})$")
    private String regNumber;

    @NotEmpty
    @Pattern(regexp = "^([4-8]{1})$")
    private String workShift;

    @NotEmpty
    @Pattern(regexp = "^(1[5-9]|2[0-5])$")
    private String loadWeight;

    @NotNull
    private Boolean working;

    @NotEmpty
    private String cityId;

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getWorkShift() {
        return workShift;
    }

    public void setWorkShift(String workShift) {
        this.workShift = workShift;
    }

    public String getLoadWeight() {
        return loadWeight;
    }

    public void setLoadWeight(String loadWeight) {
        this.loadWeight = loadWeight;
    }

    public Boolean getWorking() {
        return working;
    }

    public void setWorking(Boolean working) {
        this.working = working;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
}
