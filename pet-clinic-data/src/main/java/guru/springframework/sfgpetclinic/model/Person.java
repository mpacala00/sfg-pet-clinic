package guru.springframework.sfgpetclinic.model;

public class Person extends BaseEntity {

    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
