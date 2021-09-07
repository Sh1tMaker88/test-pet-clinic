package guru.springframework.testpetclinic.service.springDataJpa;

import guru.springframework.testpetclinic.model.Vet;
import guru.springframework.testpetclinic.repository.VetRepository;
import guru.springframework.testpetclinic.service.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springDataJpa")
public class VetSpringJpaService implements VetService {

    private final VetRepository vetRepository;

    public VetSpringJpaService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets = new HashSet<>();
        vetRepository.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public Vet findById(Long id) {
        return vetRepository.findById(id).orElseThrow(() -> new RuntimeException("No such element with ID:" + id));
    }

    @Override
    public Vet save(Vet entity) {
        return vetRepository.save(entity);
    }

    @Override
    public void delete(Vet entity) {
        vetRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        vetRepository.deleteById(id);
    }
}
