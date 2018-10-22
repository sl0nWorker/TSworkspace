package com.inc.slon.model;

import javax.persistence.*;

@Entity
@Table(name = "TRUCKERS")
public class Trucker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "PERSONAL_NUMBER")
    private int personalNumber;

    @Column(name = "WORK_HOURS")
    private int workHours;

    @OneToOne
    @JoinColumn(name = "STATUS_ID")
    private TruckerStatus status;

    @OneToOne
    @JoinColumn(name = "CITY_ID")
    private City city;

    @OneToOne
    @JoinColumn(name = "TRUCK_ID")
    private Truck truck;

    public Long getId() {
        return id;
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

    public int getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(int personalNumber) {
        this.personalNumber = personalNumber;
    }

    public int getWorkHours() {
        return workHours;
    }

    public void setWorkHours(int workHours) {
        this.workHours = workHours;
    }

    public TruckerStatus getStatus() {
        return status;
    }

    public void setStatus(TruckerStatus status) {
        this.status = status;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }
}
