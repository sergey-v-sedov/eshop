package demo.fls.eshop.registrations;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, UUID> {
    Optional<Profile> findFirstByEmail(String email);
}