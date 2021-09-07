package guru.springframework.testpetclinic.service.map;

import guru.springframework.testpetclinic.model.Visit;
import guru.springframework.testpetclinic.service.VisitService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Visit save(Visit entity) {
        if (entity.getPet() == null || entity.getPet().getId() == null || entity.getPet().getOwner() == null ||
                entity.getPet().getOwner().getId() == null) {
            throw new RuntimeException("Invalid visit");
        }

        return super.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Visit entity) {
        super.delete(entity);
    }
}
