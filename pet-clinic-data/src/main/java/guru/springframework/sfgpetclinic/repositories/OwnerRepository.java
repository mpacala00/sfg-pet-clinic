package guru.springframework.sfgpetclinic.repositories;

import guru.springframework.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//spring data JPA will provide an implementation of these repos at runtime, which is nice
public interface OwnerRepository extends CrudRepository<Owner, Long> {

    //an example of a query method:
    Owner findByLastName(String lastName);
}
