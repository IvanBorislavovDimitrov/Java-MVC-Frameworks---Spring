package com.ivan.resident_evil.model.dto.service;

import java.util.List;

public class VirusRegisterServiceModel {

    private String name;

    private String description;

    private String sideEffects;

    private String creator;

    private Boolean deadly;

    private Boolean curable;

    private String mutation;

    private double turnoverRate;

    private String magnitude;

    private String date;

    private List<String> affectedCapitals;

    private int hoursUntilTurn;

    public Boolean getDeadly() {
        return deadly;
    }

    public void setDeadly(Boolean deadly) {
        this.deadly = deadly;
    }

    public Boolean getCurable() {
        return curable;
    }

    public void setCurable(Boolean curable) {
        this.curable = curable;
    }

    public String getMutation() {
        return mutation;
    }

    public void setMutation(String mutation) {
        this.mutation = mutation;
    }

    public double getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(double turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getHoursUntilTurn() {
        return hoursUntilTurn;
    }

    public void setHoursUntilTurn(int hoursUntilTurn) {
        this.hoursUntilTurn = hoursUntilTurn;
    }

    public List<String> getAffectedCapitals() {
        return affectedCapitals;
    }

    public void setAffectedCapitals(List<String> affectedCapitals) {
        this.affectedCapitals = affectedCapitals;
    }
}
