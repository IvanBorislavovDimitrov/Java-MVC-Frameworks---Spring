package app.services.api;

import app.model.service.FindOfferServiceModel;
import app.model.service.OfferServiceModel;

import java.util.List;

public interface OfferService {

    List<OfferServiceModel> getAll();

    OfferServiceModel getById(String id);

    void save(OfferServiceModel offer);

    void findAndRemove(FindOfferServiceModel findOfferServiceModel);
}
