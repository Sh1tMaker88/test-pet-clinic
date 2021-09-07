package guru.springframework.testpetclinic.service.springDataJpa;

import guru.springframework.testpetclinic.model.Speciality;
import guru.springframework.testpetclinic.repository.SpecialityRepository;
import guru.springframework.testpetclinic.service.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springDataJpa")
public class SpecialitySpringJpaService implements SpecialityService {

    private final SpecialityRepository specialityRepository;

    public SpecialitySpringJpaService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities = new HashSet<>();
        specialityRepository.findAll().forEach(specialities::add);
        return specialities;
    }

    @Override
    public Speciality findById(Long id) {
        return specialityRepository.findById(id).orElseThrow(() -> new RuntimeException("No such element with ID:" + id));
    }

    @Override
    public Speciality save(Speciality entity) {
        return specialityRepository.save(entity);
    }

    @Override
    public void delete(Speciality entity) {
        specialityRepository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        specialityRepository.deleteById(id);
    }
}
