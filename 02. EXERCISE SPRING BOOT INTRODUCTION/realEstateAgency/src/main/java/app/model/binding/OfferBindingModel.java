package app.model.binding;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class OfferBindingModel {

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal apartmentRent;

    @NotNull
    @NotEmpty
    private String apartmentType;

    @NotNull
    @DecimalMin("0.01")
    @Max(100)
    private BigDecimal agencyCommission;

    public BigDecimal getApartmentRent() {
        return apartmentRent;
    }

    public void setApartmentRent(BigDecimal apartmentRent) {
        this.apartmentRent = apartmentRent;
    }

    public String getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    public BigDecimal getAgencyCommission() {
        return agencyCommission;
    }

    public void setAgencyCommission(BigDecimal agencyCommission) {
        this.agencyCommission = agencyCommission;
    }
}
