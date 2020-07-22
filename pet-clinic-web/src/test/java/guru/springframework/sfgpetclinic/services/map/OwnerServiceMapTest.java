package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;
    private final Long ownerId = 1L;
    private final String ownerName = "Test";

    @BeforeEach
    void setUp() {
        //mimicing what Spring will do for us - kinda like dependency injection
        //instead of interfaces we use implementation of those interfaces as arguments
        ownerServiceMap = new OwnerServiceMap(new PetTypeMapService(), new PetServiceMap());

        //initialize ownerMapService
        ownerServiceMap.save(Owner.builder().id(ownerId).lastName(ownerName).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();
        assertEquals(1, ownerSet.size());
    }

    @Test
    void findById() {
        Owner foundOwner = ownerServiceMap.findById(ownerId);
        assertEquals(foundOwner.getId(), ownerId);
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Owner owner = Owner.builder().id(id).build();
        Owner savedOwner = ownerServiceMap.save(owner);

        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner savedOwner = ownerServiceMap.save(Owner.builder().build()); //NO ID
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerId));
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerId);
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerServiceMap.findByLastName(ownerName);
        assertNotNull(owner);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findByLastNameNotFound() {
        Owner owner = ownerServiceMap.findByLastName("NotAName");
        assertNull(owner);
    }
}