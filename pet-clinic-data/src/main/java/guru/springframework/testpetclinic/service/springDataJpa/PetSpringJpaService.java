package guru.springframework.testpetclinic.service.springDataJpa;

import guru.springframework.testpetclinic.model.Pet;
import guru.springframework.testpetclinic.repository.PetRepository;
import guru.springframework.testpetclinic.service.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springDataJpa")
public class PetSpringJpaService implements PetService {

    private final PetRepository petRepository;

    public PetSpringJpaService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(Long id) {
        return petRepository.findById(id).orElseThrow(() -> new RuntimeException("No such element with ID:" + id));
    }

    @Override
    public Pet save(Pet entity) {
        return petRepository.save(entity);
    }

    @Override
    public void delete(Pet entity) {
        petRepository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }
}
