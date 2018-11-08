package com.inc.slon.model;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "TRUCKERS_HISTORY_SHIFT")
public class TruckerHistoryShift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name="TRUCKER_ID")
    private Long truckerId;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "PERSONAL_NUMBER")
    private Integer personalNumber;

    @Column(name ="SHIFT_STATUS")
    private String shiftStatus;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar shiftStatusTime;

    public TruckerHistoryShift(){}

    public TruckerHistoryShift(Long truckerId, String firstName, String lastName, Integer personalNumber, String shiftStatus, Calendar shiftStatusTime){
        this.truckerId = truckerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
        this.shiftStatus = shiftStatus;
        this.shiftStatusTime = shiftStatusTime;
    }
    public TruckerHistoryShift (Trucker trucker, String shiftStatus, Calendar shiftStatusTime){
        this.truckerId = trucker.getId();
        this.firstName = trucker.getFirstName();
        this.lastName = trucker.getLastName();
        this.personalNumber = trucker.getPersonalNumber();
        this.shiftStatus = shiftStatus;
        this.shiftStatusTime = shiftStatusTime;
    }

    public Long getId() {
        return id;
    }

    public Long getTruckerId() {
        return truckerId;
    }

    public void setTruckerId(Long truckerId) {
        this.truckerId = truckerId;
    }

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

    public Integer getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(Integer personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getShiftStatus() {
        return shiftStatus;
    }

    public void setShiftStatus(String shiftStatus) {
        this.shiftStatus = shiftStatus;
    }

    public Calendar getShiftStatusTime() {
        return shiftStatusTime;
    }

    public void setShiftStatusTime(Calendar shiftStatusTime) {
        this.shiftStatusTime = shiftStatusTime;
    }

    @Override
    public String toString(){
        return "truckerId: " + truckerId + ", trucker First name: " + firstName + ", trucker Last name: " + lastName
                + ", trucker personal number: " + personalNumber + ", trucker shift status: " + shiftStatus
                + ", shiftStatusTime: " + shiftStatusTime;
    }

}
