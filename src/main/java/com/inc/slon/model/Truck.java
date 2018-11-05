package com.inc.slon.model;

import javax.persistence.*;

@Entity
@Table(name = "TRUCKS")
public class Truck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "REGNUMBER")
    private String regNumber;

    @Column(name = "WORK_SHIFT")
    private int workShift;

    @Column(name = "LOAD_WEIGHT")
    private int loadWeight;

    @Column(name = "WORKING")
    private Boolean working;

    @OneToOne
    @JoinColumn(name = "CITY_ID")
    private City city;

    @OneToOne(mappedBy = "truck")
    private Order order;


    public Truck(){
    }

    public Truck(String regNumber, int workShift,int loadWeight, Boolean working, City city){
        this.regNumber = regNumber;
        this.workShift = workShift;
        this.loadWeight = loadWeight;
        this.working = working;
        this.city = city;
    }
    public Long getId() {
        return id;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public int getWorkShift() {
        return workShift;
    }

    public void setWorkShift(int workShift) {

        this.workShift = workShift;
    }

    public int getLoadWeight() {
        return loadWeight;
    }

    public void setLoadWeight(int loadWeight) {
        this.loadWeight = loadWeight;
    }

    public Boolean getWorking() {
        return working;
    }

    public void setWorking(Boolean working) {
        this.working = working;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString(){
        //TODO: delete id (id for testing)
        return "regNumber: " + regNumber + ", id: " + id;
    }

    public Order getOrder() {
        return order;
    }
}
