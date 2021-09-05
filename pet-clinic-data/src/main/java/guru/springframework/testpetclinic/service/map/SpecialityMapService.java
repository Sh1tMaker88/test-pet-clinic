package guru.springframework.testpetclinic.service.map;

import guru.springframework.testpetclinic.model.Speciality;
import guru.springframework.testpetclinic.service.SpecialityService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SpecialityMapService extends AbstractMapService<Speciality, Long> implements SpecialityService {

    @Override
    public Set<Speciality> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Speciality entity) {
        super.delete(entity);
    }

    @Override
    public Speciality save(Speciality entity) {
        return super.save(entity);
    }

    @Override
    public Speciality findById(Long id) {
        return super.findById(id);
    }
}
