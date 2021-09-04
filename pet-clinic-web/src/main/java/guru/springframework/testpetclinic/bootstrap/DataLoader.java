package guru.springframework.testpetclinic.bootstrap;

import guru.springframework.testpetclinic.model.Owner;
import guru.springframework.testpetclinic.model.Pet;
import guru.springframework.testpetclinic.model.PetType;
import guru.springframework.testpetclinic.model.Vet;
import guru.springframework.testpetclinic.service.OwnerService;
import guru.springframework.testpetclinic.service.PetTypeService;
import guru.springframework.testpetclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final VetService vetService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public DataLoader(VetService vetService, OwnerService ownerService, PetTypeService petTypeService) {
        this.vetService = vetService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("123123");

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("123123");
        System.out.println("--Loaded Owners--");

        Pet michaelPet = new Pet("Rosko", savedDogPetType, owner1, LocalDate.now());
        owner1.getPets().add(michaelPet);
        ownerService.save(owner1);

        Pet fionaCat = new Pet("Just Cat", savedCatPetType, owner2, LocalDate.now());
        owner2.getPets().add(fionaCat);
        ownerService.save(owner2);

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vetService.save(vet2);
        System.out.println("--Loaded Vets--");
    }
}
