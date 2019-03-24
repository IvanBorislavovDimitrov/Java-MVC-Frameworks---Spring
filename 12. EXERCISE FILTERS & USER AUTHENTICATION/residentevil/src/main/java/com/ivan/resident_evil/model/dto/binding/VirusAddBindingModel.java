package com.ivan.resident_evil.model.dto.binding;

import com.ivan.resident_evil.validation.annotations.CreatorType;
import com.ivan.resident_evil.validation.annotations.MagnitudeAnnotation;
import com.ivan.resident_evil.validation.annotations.Mutator;
import com.ivan.resident_evil.validation.annotations.ReleasedOn;

import javax.validation.constraints.*;
import java.util.List;

public class VirusAddBindingModel {

    private static final String INVALID_NAME = "Invalid name";
    private static final String INVALID_DESCRIPTION = "Invalid description";
    private static final String INVALID_SIDE_EFFECTS = "Invalid side effects";
    private static final String INVALID_CREATOR = "Invalid creator";
    private static final String INVALID_MUTATION = "Invalid mutation";
    private static final String INVALID_TURNOVER_RATE = "Invalid turnover rate";
    private static final String INVALID_MAGNITUDE = "Invalid magnitude";
    private static final String INVALID_RELEASED_ON = "Invalid released on";
    private static final String INVALID_IS_DEADLY = "IsDeadly cannot be null";
    private static final String INVALID_IS_CURABLE = "IsCurable cannot be null";
    private static final String INVALID_AFFECTED_CAPITALS = "Select affected capitals";

    @Size(min = 3, max = 10, message = INVALID_NAME)
    private String name;

    @Size(min = 5, max = 100, message = INVALID_DESCRIPTION)
    private String description;

    @Size(min = 1, max = 50, message = INVALID_SIDE_EFFECTS)
    private String sideEffects;

    @CreatorType(message = INVALID_CREATOR)
    private String creator;

    @NotNull(message = INVALID_IS_DEADLY)
    private Boolean deadly;

    @NotNull(message = INVALID_IS_CURABLE)
    private Boolean curable;

    @Mutator(message = INVALID_MUTATION)
    private String mutation;

    @Min(value = 0, message = INVALID_TURNOVER_RATE)
    @Max(value = 100, message = INVALID_TURNOVER_RATE)
    private double turnoverRate;

    @MagnitudeAnnotation(message = INVALID_MAGNITUDE)
    private String magnitude;

    @ReleasedOn(message = INVALID_RELEASED_ON)
    private String date;

    @NotEmpty(message = INVALID_AFFECTED_CAPITALS)
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
