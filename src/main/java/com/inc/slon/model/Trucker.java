package com.inc.slon.model;

import com.inc.slon.model.form.TruckerForm;

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

    @Column(name = "PERSONAL_NUMBER", unique = true)
    private Integer personalNumber;

    @Column(name = "WORK_HOURS")
    private Integer workHours;

    @OneToOne
    @JoinColumn(name = "STATUS_ID")
    private TruckerStatus status;

    @OneToOne
    @JoinColumn(name = "CITY_ID")
    private City city;

    @OneToOne
    @JoinColumn(name = "TRUCK_ID")
    private Truck truck;

    public Trucker(){}

    public Trucker(TruckerForm truckerForm){
        firstName = truckerForm.getFirstName();
        lastName = truckerForm.getLastName();
        personalNumber = Integer.valueOf(truckerForm.getPersonalNumber());
        workHours = 0;
    }

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

    public Integer getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(Integer personalNumber) {
        this.personalNumber = personalNumber;
    }

    public Integer getWorkHours() {
        return workHours;
    }

    public void setWorkHours(Integer workHours) {
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

    @Override
    public String toString(){
        return firstName + " " + lastName + " " + personalNumber;
    }
}
