package com.inc.slon.model;

import javax.persistence.*;

@Entity
@Table (name = "TRUCKER_STATUS")
public class TruckerStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name ="STATUS")
    private String status;

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
