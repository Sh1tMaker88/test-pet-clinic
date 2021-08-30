package guru.springframework.testpetclinic.service.map;

import guru.springframework.testpetclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

    protected Map<Long, T> map = new HashMap<>();

    Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    T findById(ID id) {
        return map.get(id);
    }

    T save(T entity) {
        if (entity != null) {
            if (entity.getId() == null) {
                entity.setId(getNextId());
            }
            map.put(entity.getId(), entity);
        } else {
            throw new RuntimeException("Object cannot be null");
        }

        return entity;
    }

    void delete(T entity) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(entity));
    }

    void deleteById(ID id) {
        map.remove(id);
    }

    private Long getNextId() {
        if (map.isEmpty()) {
            return 1L;
        }
        return Collections.max(map.keySet()) + 1;
    }
}
