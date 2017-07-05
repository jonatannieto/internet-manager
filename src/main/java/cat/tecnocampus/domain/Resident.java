package cat.tecnocampus.domain;

import javax.persistence.*;

/**
 * Created by Jonatan on 05/07/2017.
 */
@Entity
public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    private String nif;

    private String name;

    public Resident() {}

    public Resident(String nif, String name) {
        this.nif = nif;
        this.name = name;
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
}
