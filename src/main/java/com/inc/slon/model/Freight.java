package com.inc.slon.model;

import javax.persistence.*;

@Entity
@Table(name = "FREIGHTS")
public class Freight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NUMBER")
    private int number;

    @Column(name = "NAME")
    private String name;

    @Column(name = "WEIGHT")
    private int weight;

    @OneToOne
    @JoinColumn(name = "STATUS_ID")
    private FreightStatus freightStatus;

    public Long getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public FreightStatus getFreightStatus() {
        return freightStatus;
    }

    public void setFreightStatus(FreightStatus freightStatus) {
        this.freightStatus = freightStatus;
    }
}
