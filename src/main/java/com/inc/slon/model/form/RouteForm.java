package com.inc.slon.model.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

public class RouteForm {
    @NotEmpty
    private String cityId;
    @Pattern(regexp = "^([0-9]{1,9})$")
    private String freightNumber;
    @Pattern(regexp = "^([A-Za-z]{1,25})$")
    private String freightName;
    @Max(value = 25000)
    @Pattern(regexp = "^([0-9]|[1-9]{1}[0-9]{1,4})$")
    private String weight;
    @NotEmpty
    private String loading;


    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getFreightNumber() {
        return freightNumber;
    }

    public void setFreightNumber(String freightNumber) {
        this.freightNumber = freightNumber;
    }

    public String getFreightName() {
        return freightName;
    }

    public void setFreightName(String freightName) {
        this.freightName = freightName;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getLoading() {
        return loading;
    }

    public void setLoading(String loading) {
        this.loading = loading;
    }
}

