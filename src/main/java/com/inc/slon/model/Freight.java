package com.inc.slon.model;

import javax.persistence.*;

@Entity
@Table(name = "FREIGHTS")
public class Freight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column
    private Integer freightNumber;

    @Column(name = "NAME")
    private String name;

    @Column(name = "WEIGHT")
    private Integer weight;

    @OneToOne
    @JoinColumn(name = "STATUS_ID")
    private FreightStatus freightStatus;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public FreightStatus getFreightStatus() {
        return freightStatus;
    }

    public void setFreightStatus(FreightStatus freightStatus) {
        this.freightStatus = freightStatus;
    }

    @Override
    public String toString(){
        //TODO: remove id, id for testing
        return "number: " + freightNumber + ", name: " + name + " m(kg): " + weight + " id: " + id;
    }

    public Integer getFreightNumber() {
        return freightNumber;
    }

    public void setFreightNumber(Integer freightNumber) {
        this.freightNumber = freightNumber;
    }
}
