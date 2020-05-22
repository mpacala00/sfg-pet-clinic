package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
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

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

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
