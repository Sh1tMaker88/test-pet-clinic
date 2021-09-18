package guru.springframework.testpetclinic.service;

import guru.springframework.testpetclinic.model.Pet;
import guru.springframework.testpetclinic.service.map.PetMapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PetMapServiceTest {

    private PetMapService petMapService;

    private final Long petId = 1L;

    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();
        petMapService.save(Pet.builder().id(petId).build());
    }

    @Test
    void findAll() {
        Set<Pet> petSet = petMapService.findAll();

        assertEquals(1, petSet.size());
    }

    @Test
    void findByExistingId() {
        Pet pet = petMapService.findById(petId);

        assertEquals(petId, pet.getId());
    }

    @Test
    void findByIdNull() {
        Pet pet = petMapService.findById(null);

        assertNull(pet);
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Pet pet2 = Pet.builder().id(id).build();

        Pet savedPet = petMapService.save(pet2);

        assertEquals(id, savedPet.getId());
    }

    @Test
    void saveDuplicateId() {
        Long id = 1L;
        Pet pet2 = Pet.builder().id(id).build();

        Pet savedPet = petMapService.save(pet2);

        assertEquals(id, savedPet.getId());
        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void saveNoId() {
        Pet savedPet = petMapService.save(Pet.builder().build());

        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
        assertEquals(2, petMapService.findAll().size());
    }

    @Test
    void deletePet() {
        petMapService.delete(petMapService.findById(petId));

        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void deleteWithWrongId() {
        Pet pet = Pet.builder().id(5L).build();

        petMapService.delete(pet);

        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void deleteWithNullId() {
        Pet pet = Pet.builder().build();

        petMapService.delete(pet);

        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void deleteNull() {
        petMapService.delete(null);

        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void deleteById() {
        petMapService.deleteById(petId);

        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void deleteByWrongId() {
        petMapService.deleteById(5L);

        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void deleteByIdNull() {
        petMapService.deleteById(null);

        assertEquals(1, petMapService.findAll().size());
    }
}
