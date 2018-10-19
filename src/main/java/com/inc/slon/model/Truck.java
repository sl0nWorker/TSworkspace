package com.inc.slon.model;

import javax.persistence.*;

@Entity
@Table(name = "TRUCK")
public class Truck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "GOODCONDITION")
    private boolean goodCondition;

    @Column(name = "NAME")
    private String name;

    public boolean isGoodCondition() {
        return goodCondition;
    }

    public void setGoodCondition(boolean goodCondition) {
        this.goodCondition = goodCondition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
