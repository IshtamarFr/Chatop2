package fr.ishtamar.chatop.service;

import fr.ishtamar.chatop.entity.Rental;
import fr.ishtamar.chatop.repository.RentalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class RentalService {
    @Autowired
    private RentalRepository repository;

    public List<Rental> getAllRentals() {
        return repository.findAll();
    }

    public Rental getRentalById(final Long id) throws EntityNotFoundException {
        Optional<Rental>candidate=repository.findById(id);
        if (candidate.isPresent()) {
            return candidate.get();
        } else {
            throw new EntityNotFoundException();
        }
    }
}
