package fr.ishtamar.chatop.repository;

import fr.ishtamar.chatop.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    //[X-01] Choose findByName or findByEmail
    Optional<UserInfo> findByEmail(String username);
}
