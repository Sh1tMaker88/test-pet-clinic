package guru.springframework.testpetclinic.bootstrap;

import guru.springframework.testpetclinic.model.*;
import guru.springframework.testpetclinic.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final VetService vetService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(VetService vetService, OwnerService ownerService, PetTypeService petTypeService,
                      SpecialityService specialityService, VisitService visitService) {
        this.vetService = vetService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {

        PetType dog = new PetType("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality("Radiology");
        Speciality surgery = new Speciality("Surgery");
        Speciality dentistry = new Speciality("Dentistry");
        Speciality savedRadiology = specialityService.save(radiology);
        Speciality savedSurgery = specialityService.save(surgery);
        Speciality savedDentistry = specialityService.save(dentistry);

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

        Visit catVisit = new Visit(LocalDate.now(), "Sneezy kitty", fionaCat);
        visitService.save(catVisit);

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(savedSurgery);
        vetService.save(vet2);
        System.out.println("--Loaded Vets--");
    }
}
