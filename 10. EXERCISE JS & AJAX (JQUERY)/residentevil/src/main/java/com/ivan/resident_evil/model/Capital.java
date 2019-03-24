package com.ivan.resident_evil.model;

import javax.persistence.*;

@Entity
@Table(name = "capitals")
public class Capital extends BaseEntity {

    @Basic
    private String name;

    @Basic
    private double latitude;

    @Basic
    private double longitude;

    @ManyToOne
    @JoinColumn(name = "virus_id", referencedColumnName = "id")
    private Virus virus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Virus getVirus() {
        return virus;
    }

    public void setVirus(Virus virus) {
        this.virus = virus;
    }
}

