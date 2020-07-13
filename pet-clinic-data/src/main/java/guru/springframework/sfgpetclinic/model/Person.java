package guru.springframework.sfgpetclinic.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass //again, annotate to tell JPA just to inherit from this class and not put it into db
public class Person extends BaseEntity {

    @Column(name = "first_name")//column name in the database
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
