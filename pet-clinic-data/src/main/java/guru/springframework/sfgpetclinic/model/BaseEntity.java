package guru.springframework.sfgpetclinic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass //means that its the base class for JPA that other classes will be inheriting from
//but we don't need this specific class put into database
public class BaseEntity implements Serializable {

    //Hibernate recommends using Long instead of long
    //Box types can be null
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
