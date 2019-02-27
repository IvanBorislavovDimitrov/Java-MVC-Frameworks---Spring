package app.services.impl;

import app.entitites.Offer;
import app.model.service.FindOfferServiceModel;
import app.model.service.OfferServiceModel;
import app.repositories.OfferRepository;
import app.services.api.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
    }

    public List<OfferServiceModel> getAll() {
        return offerRepository.findAll().stream()
                .map(offer -> modelMapper.map(offer, OfferServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public OfferServiceModel getById(String id) {
        Offer offer = offerRepository.getById(id);
        if (offer == null) {
            return null;
        }

        return modelMapper.map(offer, OfferServiceModel.class);
    }

    @Override
    public void save(OfferServiceModel offerServiceModel) {
        offerRepository.save(modelMapper.map(offerServiceModel, Offer.class));
    }

    @Override
    public void findAndRemove(FindOfferServiceModel findOfferServiceModel) {
        Offer offer = offerRepository.findByApartmentType(findOfferServiceModel.getApartmentType());
        if (offer == null) {
            throw new IllegalArgumentException("Offer not found");
        }
        if (findOfferServiceModel.getFamilyBudget().compareTo(offer.getApartmentRent().add(offer.getAgencyCommission().multiply(offer.getApartmentRent().divide(BigDecimal.valueOf(100))))) > 0) {
            offerRepository.delete(offer);
        }
    }
}
