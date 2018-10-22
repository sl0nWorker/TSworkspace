package com.inc.slon.model;

import javax.persistence.*;

@Entity
@Table(name = "ROUTES")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "CITY_ID")
    private City city;

    @OneToOne
    @JoinColumn(name = "FREIGHT_ID")
    private Freight freight;

    @Column(name ="TYPE")
    private Boolean unloading;

    public Long getId() {
        return id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Freight getFreight() {
        return freight;
    }

    public void setFreight(Freight freight) {
        this.freight = freight;
    }

    public Boolean getUnloading() {
        return unloading;
    }

    public void setUnloading(Boolean unloading) {
        this.unloading = unloading;
    }
}
