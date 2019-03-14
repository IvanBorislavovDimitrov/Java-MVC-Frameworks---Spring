package com.ivan.resident_evil.model.dto.binding;

import com.ivan.resident_evil.validation.annotations.CreatorType;
import com.ivan.resident_evil.validation.annotations.MagnitudeAnnotation;
import com.ivan.resident_evil.validation.annotations.Mutator;
import com.ivan.resident_evil.validation.annotations.ReleasedOn;

import javax.validation.constraints.*;
import java.util.Date;

public class VirusAddBindingModel {

    private static final String INVALID_NAME = "Invalid name";
    private static final String INVALID_DESCRIPTION = "Invalid description";
    private static final String INVALID_SIDE_EFFECTS = "Invalid side effects";
    private static final String INVALID_CREATOR = "Invalid creator";
    private static final String INVALID_MUTATION = "Invalid mutation";
    private static final String INVALID_TURNOVER_RATE = "Invalid turnover rate";
    private static final String INVALID_MAGNITUDE = "Invalid magnitude";
    private static final String INVALID_RELEASED_ON = "Invalid released on";

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    @Size(min = 3, max = 10, message = INVALID_NAME)
    private String name;

    @NotNull(message = INVALID_DESCRIPTION)
    @NotNull(message = INVALID_DESCRIPTION)
    @Size(min = 3, max = 10, message = INVALID_DESCRIPTION)
    private String description;

    @NotNull(message = INVALID_SIDE_EFFECTS)
    @NotEmpty(message = INVALID_SIDE_EFFECTS)
    @Size(max = 50, message = INVALID_SIDE_EFFECTS)
    private String sideEffects;

    @NotNull(message = INVALID_CREATOR)
    @NotEmpty(message = INVALID_CREATOR)
    @CreatorType(message = INVALID_CREATOR)
    private String creator;

    private boolean isDeadly;

    private boolean isCurable;

    @NotNull(message = INVALID_MUTATION)
    @Mutator(message = INVALID_MUTATION)
    private String mutation;

    @Min(value = 0, message = INVALID_TURNOVER_RATE)
    @Max(value = 100, message = INVALID_TURNOVER_RATE)
    private double turnoverRate;

    @NotNull(message = INVALID_MAGNITUDE)
    @MagnitudeAnnotation(message = INVALID_MAGNITUDE)
    private String magnitude;

    @NotNull(message = INVALID_RELEASED_ON)
    @ReleasedOn(message = INVALID_RELEASED_ON)
    private Date releasedOn;

    public static String getInvalidName() {
        return INVALID_NAME;
    }

    public static String getInvalidDescription() {
        return INVALID_DESCRIPTION;
    }

    public static String getInvalidSideEffects() {
        return INVALID_SIDE_EFFECTS;
    }

    public static String getInvalidCreator() {
        return INVALID_CREATOR;
    }

    public static String getInvalidMutation() {
        return INVALID_MUTATION;
    }

    public static String getInvalidTurnoverRate() {
        return INVALID_TURNOVER_RATE;
    }

    public static String getInvalidMagnitude() {
        return INVALID_MAGNITUDE;
    }

    public static String getInvalidReleasedOn() {
        return INVALID_RELEASED_ON;
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

    public Date getReleasedOn() {
        return releasedOn;
    }

    public void setReleasedOn(Date releasedOn) {
        this.releasedOn = releasedOn;
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
}
