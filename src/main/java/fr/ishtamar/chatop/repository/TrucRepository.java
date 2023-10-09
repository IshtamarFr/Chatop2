package fr.ishtamar.chatop.repository;

import fr.ishtamar.chatop.entity.Truc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrucRepository extends JpaRepository<Truc, Long> {
}
