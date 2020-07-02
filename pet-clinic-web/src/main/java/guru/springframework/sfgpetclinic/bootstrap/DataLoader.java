package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import guru.springframework.sfgpetclinic.services.OwnerService;

//implementation is spring boot specific way of implementing data
@Component //becomes a spring bean because of this annotation and is put into the context on startup
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService; //option+enter -> add constructor param.

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");

        //saving as a PetType to later use to wire up to owners etc.
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner("Lukas", "Wilder");
        ownerService.save(owner1);

        Owner owner2 = new Owner("Bevan", "Compton");
        ownerService.save(owner2);

        System.out.println("Owners loaded");

        Vet vet1 = new Vet("Adeline", "Walker");
        vetService.save(vet1);

        Vet vet2 = new Vet("Jason", "Romero");
        vetService.save(vet2);

        System.out.println("Vets loaded");

    }
}
