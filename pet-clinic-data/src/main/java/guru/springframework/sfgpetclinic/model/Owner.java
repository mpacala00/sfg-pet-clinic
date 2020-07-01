package guru.springframework.sfgpetclinic.model;

import java.util.Set;

public class Owner extends Person {

    private Set<Pet> pets;

    public Owner(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
}
