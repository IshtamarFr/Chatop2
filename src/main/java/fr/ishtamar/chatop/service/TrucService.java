package fr.ishtamar.chatop.service;

import fr.ishtamar.chatop.entity.Truc;
import fr.ishtamar.chatop.repository.TrucRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class TrucService {
    @Autowired
    private TrucRepository repository;

    public List<Truc> getAllTrucs() {
        return repository.findAll();
    }

    public Truc getTrucById(final Long id) throws EntityNotFoundException {
        Optional<Truc>candidate=repository.findById(id);
        if (candidate.isPresent()) {
            return candidate.get();
        } else {
            throw new EntityNotFoundException();
        }
    }
}
