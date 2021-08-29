package guru.springframework.testpetclinic.service;

import guru.springframework.testpetclinic.model.Vet;

import java.util.Set;

public interface VetService {

    Vet finById(Long id);

    Vet save(Vet vet);

    Set<Vet> findAllPets();
}
