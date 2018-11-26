package com.inc.slon.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "READY")
    private Boolean ready;

    //magic
    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "ROUTES")
    private List<Route> routeList;

    @OneToOne
    @JoinColumn(name = "TRUCK_ID")
    private Truck truck;

    //magic
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ORDER_TRUCKERS", joinColumns = @JoinColumn(name = "ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name = "TRUCKER_ID"))
    private List<Trucker> truckerList;

    public Long getId() {
        return id;
    }

    public Boolean getReady() {
        return ready;
    }

    public void setReady(Boolean ready) {
        this.ready = ready;
    }

    public List<Route> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<Route> routeList) {
        this.routeList = routeList;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public List<Trucker> getTruckerList() {
        return truckerList;
    }

    public void setTruckerList(List<Trucker> truckerList) {
        this.truckerList = truckerList;
    }

    public String showTruckerList() {
        if (truckerList != null && truckerList.size() != 0) {
            StringBuilder sb = new StringBuilder();
            int i;
            for (i = 0; i < truckerList.size() - 1; i++) {
                sb.append(truckerList.get(i).getLastName() + " " + truckerList.get(i).getFirstName() + ", ");
            }
            sb.append(truckerList.get(i).getLastName() + " " + truckerList.get(i).getFirstName() + ".");
            return sb.toString();
        } else {
            return "empty trucker list";
        }
    }

    public String showRouteList() {
        if (routeList != null && routeList.size() != 0) {
            StringBuilder sb = new StringBuilder();
            int i;
            for (i = 0; i < routeList.size() - 1; i++) {
                sb.append(routeList.get(i).getCity() + "-");
            }
            sb.append(routeList.get(i).getCity() + ".");
            return sb.toString();
        } else {
            return "empty route list";
        }
    }
}
