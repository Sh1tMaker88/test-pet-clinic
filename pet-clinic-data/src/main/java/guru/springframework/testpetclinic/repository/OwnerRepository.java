package guru.springframework.testpetclinic.repository;

import guru.springframework.testpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Optional<Owner> findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
