package cat.tecnocampus.domain;

import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by internet-manager on 11/04/17.
 */
@Entity
public class Community {
    /* Indicamos a Spring-Data (JPA) que este campo es un campo ID en Base de datos */
    /* Indicamos a Spring-Data (JPA) que es autoincrement */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /* Control de versionado, si a futuro cambiamos las entidades tenemos control de cuando se ha creado un registro, es decir en que
    versión de base de datos se ha añadido. */
    @Version
    private Integer version;

    private String nif;

    private String name;

    @OneToOne
    private City city;

    @OneToMany
    private List<Resident> residents;

    private String address;

    public Community() {}

    public Community(String nif, String name, City city, String address) {
        this.nif = nif;
        this.name = name;
        this.city = city;
        this.address = address;
        this.residents = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Resident> getResidents() {
        return residents;
    }

    public void addResident(Resident resident) {
        this.residents.add(resident);
    }
}
