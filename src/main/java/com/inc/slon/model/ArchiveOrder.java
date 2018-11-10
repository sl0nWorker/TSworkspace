package com.inc.slon.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="ARCHIVE_ORDER")
public class ArchiveOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name= "ORDER_ID")
    private Long orderId;

    @Column(name = "READY")
    private Boolean ready;

    @Column(name = "ROUTE_LIST", columnDefinition="TEXT")
    private String routeList;

    @Column(name = "TRUCK")
    private String truck;

    @Column(name = "TRUCKER_LIST")
    private String truckerList;


    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Boolean getReady() {
        return ready;
    }

    public String getRouteList() {
        return routeList;
    }

    public String getTruck() {
        return truck;
    }

    public String getTruckerList() {
        return truckerList;
    }

    public ArchiveOrder(){}

    public ArchiveOrder(Order order){
        orderId = order.getId();
        ready = true;

        StringBuilder sb = new StringBuilder();
        List<Route> routesList = order.getRouteList();
        int i = 0;
        for (i = 0; i < routesList.size() - 1; i++) {
            sb.append(routesList.get(i).toString() + ", ");
        }
        sb.append(routesList.get(i).toString() + ".");
        routeList = sb.toString();

        truck = order.getTruck().toString();

        sb = new StringBuilder();
        List<Trucker> truckersList = order.getTruckerList();
        i = 0;
        for (i = 0; i < truckersList.size() - 1; i++) {
            sb.append(truckersList.get(i).toString() +", ");
        }
        sb.append(truckersList.get(i).toString() + ".");
        truckerList = sb.toString();
    }

    @Override
    public String toString(){
        return "orderId: " + orderId
                +", ready: " + ready
                +", routeList: " + routeList
                +", truck: " + truck
                +", truckerList: " + truckerList;
    }
}
