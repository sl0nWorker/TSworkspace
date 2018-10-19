package com.inc.slon.model;

import javax.persistence.*;

@Entity
@Table(name = "TRUCKS")
public class Truck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "REGNUMBER")
    private String regNumber;

    @Column(name = "WORKSHIFT")
    private int workShift;

    @Column(name = "LOADCAPACITY")
    private int loadCapacity;

    @Column(name = "WORKING")
    private boolean working;

    @OneToOne
    @JoinColumn(name = "CURRENTCITY")
    private City city;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public int getWorkShift() { return workShift; }

    public void setWorkShift(int workShift) { this.workShift = workShift; }

    public int getLoadCapacity() { return loadCapacity; }

    public void setLoadCapacity(int loadCapacity) { this.loadCapacity = loadCapacity; }

    public boolean getWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    public City getCity() { return city; }

    public void setCity(City city) { this.city = city; }
}
