package com.ivan.resident_evil.model.entities;

import com.ivan.resident_evil.model.enums.Creator;
import com.ivan.resident_evil.model.enums.Magnitude;
import com.ivan.resident_evil.model.enums.Mutation;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "viruses")
public class Virus extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "sideEffects")
    private String sideEffects;

    @Column(name = "creator")
    @Enumerated(EnumType.STRING)
    private Creator creator;

    @Column(name = "is_deadly")
    private boolean isDeadly;

    @Column(name = "is_curable")
    private boolean isCurable;

    @Column(name = "mutation"   )
    private Mutation mutation;

    @Column(name = "turnover_rate")
    private BigDecimal turnoverRate;

    @Column(name = "hours_until_turn")
    private byte hoursUntilTurn;

    @Column(name = "magnitude", nullable = false)
    private Magnitude magnitude;

    @Column(name = "released_on", nullable = false)
    private Date releasedOn;

    @OneToMany(mappedBy = "virus", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Capital.class)
    private List<Capital> capitals;

    public Virus() {
        capitals = new ArrayList<>();
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

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public boolean isDeadly() {
        return isDeadly;
    }

    public void setDeadly(boolean deadly) {
        isDeadly = deadly;
    }

    public boolean isCurable() {
        return isCurable;
    }

    public void setCurable(boolean curable) {
        isCurable = curable;
    }

    public Mutation getMutation() {
        return mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    public BigDecimal getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(BigDecimal turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public byte getHoursUntilTurn() {
        return hoursUntilTurn;
    }

    public void setHoursUntilTurn(byte hoursUntilTurn) {
        this.hoursUntilTurn = hoursUntilTurn;
    }

    public Magnitude getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }

    public Date getReleasedOn() {
        return releasedOn;
    }

    public void setReleasedOn(Date releasedOn) {
        this.releasedOn = releasedOn;
    }

    public List<Capital> getCapitals() {
        return capitals;
    }

    public void setCapitals(List<Capital> capitals) {
        this.capitals = capitals;
    }
}
