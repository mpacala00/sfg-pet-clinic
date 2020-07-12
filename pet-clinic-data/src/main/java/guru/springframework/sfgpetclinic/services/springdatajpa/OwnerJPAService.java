package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class OwnerJPAService implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    public OwnerJPAService(OwnerRepository ownerRepository, PetRepository petRepository,
                           PetTypeRepository petTypeRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName); //implementing this method by having it in the repo interface
    }

    @Override
    public Set<Owner> findAll() {

        Set<Owner> ownerSet = new HashSet<>();

        ownerRepository.findAll().forEach(owner -> { ownerSet.add(owner); });
        //ownerRepository.findAll().forEach(ownerSet::add); another way of doing this

        return ownerSet;
    }

    @Override
    public Owner findById(Long aLong) {
        Optional<Owner> ownerOpt = ownerRepository.findById(aLong);

        if(ownerOpt.isPresent()) {
            return ownerOpt.get(); //.get() because it returns Optional
        } else {
            return null;
        }

        //we can replace the whole if else statement with this single line (functional style):
        //return ownerOpt.orElse(null);
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        ownerRepository.deleteById(aLong);
    }
}
