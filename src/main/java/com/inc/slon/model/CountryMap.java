package com.inc.slon.model;

import javax.persistence.*;

@Entity
@Table(name = "COUNTRY_MAP")
public class CountryMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "CITY_ID_FROM")
    private City cityFrom;

    @OneToOne
    @JoinColumn(name = "CITY_ID_TO")
    private City cityTo;

    @Column(name = "DISTANCE")
    private int distance;

    public CountryMap(){}

    public CountryMap(City cityFrom, City cityTo, int distance){
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.distance = distance;
    }

    public Long getId() {
        return id;
    }

    public City getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(City cityFrom) {
        this.cityFrom = cityFrom;
    }

    public City getCityTo() {
        return cityTo;
    }

    public void setCityTo(City cityTo) {
        this.cityTo = cityTo;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}

