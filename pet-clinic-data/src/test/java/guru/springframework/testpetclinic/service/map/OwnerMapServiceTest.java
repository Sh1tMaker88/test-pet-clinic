package guru.springframework.testpetclinic.service.map;

import guru.springframework.testpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;

    final Long ownerId = 1L;
    final String ownerLastName = "Test_name";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

        ownerMapService.save(Owner.builder().id(ownerId).lastName(ownerLastName).build());
    }

    @Test
    @DisplayName("Find_All method")
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();

        assertEquals(1, owners.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);

        assertEquals(ownerLastName, owner.getLastName());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Owner owner2 = Owner.builder().id(id).build();
        Owner savedOwner = ownerMapService.save(owner2);

        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner savedOwner2 = ownerMapService.save(Owner.builder().build());

        assertNotNull(savedOwner2.getId());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerMapService.findByLastName(ownerLastName);
        assertEquals(ownerLastName, owner.getLastName());
    }

    @Test
    void findByLastName_throwNuSuchElementException() {

        assertThrows(NoSuchElementException.class, () -> ownerMapService.findByLastName("test") );
    }
}