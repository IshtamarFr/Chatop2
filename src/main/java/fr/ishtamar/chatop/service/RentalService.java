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

    /**
     * Gets all rentals
     * @return List of all rentals
     */
    public List<Rental> getAllRentals() {
        return repository.findAll();
    }

    /**
     * Gets a rental by Id if it exists
     * @param id - id for rental
     * @return Rental
     * @throws EntityNotFoundException
     */
    public Rental getRentalById(final Long id) throws EntityNotFoundException {
        Optional<Rental>candidate=repository.findById(id);
        if (candidate.isPresent()) {
            return candidate.get();
        } else {
            throw new EntityNotFoundException();
        }
    }
}
