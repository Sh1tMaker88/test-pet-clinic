package guru.springframework.testpetclinic.service.springDataJpa;

import guru.springframework.testpetclinic.model.Owner;
import guru.springframework.testpetclinic.repository.OwnerRepository;
import guru.springframework.testpetclinic.repository.PetRepository;
import guru.springframework.testpetclinic.repository.PetTypeRepository;
import guru.springframework.testpetclinic.service.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springDataJpa")
public class OwnerSpringJpaService implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    public OwnerSpringJpaService(OwnerRepository ownerRepository, PetRepository petRepository,
                                 PetTypeRepository petTypeRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Owner findByLastName(String lastName) {
        Optional<Owner> ownerOptional = ownerRepository.findByLastName(lastName);
        if (ownerOptional.isEmpty()) {
            throw new RuntimeException("No such last name in database");
        }

        return ownerOptional.get();
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return ownerRepository.findAllByLastNameLike(lastName);
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owners::add);

        return owners;
    }

    @Override
    public Owner findById(Long id) {
        if (ownerRepository.findById(id).isEmpty()) {
            throw new RuntimeException("No such entity in database");
        }

        return ownerRepository.findById(id).get();
    }

    @Override
    public Owner save(Owner entity) {
        return ownerRepository.save(entity);
    }

    @Override
    public void delete(Owner entity) {
        ownerRepository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }
}
