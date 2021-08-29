package guru.springframework.testpetclinic.service;

import guru.springframework.testpetclinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet finById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAllPets();
}
