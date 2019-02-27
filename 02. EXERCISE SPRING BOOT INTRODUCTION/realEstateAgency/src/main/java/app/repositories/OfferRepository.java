package app.repositories;

import app.entitites.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, String> {

    Offer getById(String id);

    Offer findByApartmentType(String apartmentType);
}
