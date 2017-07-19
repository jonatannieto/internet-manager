package cat.tecnocampus.domain;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Jonatan on 19/07/2017.
 */
@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    private Date dateStart;

    @ManyToOne
    @JoinColumn(name="contract_id", nullable=false)
    private Contract contract;

    @ManyToOne
    @JoinColumn(name="resident_id", nullable=false)
    private Resident resident;

    private Double subtotal;

    private Double vat;

    private Double total;

    public Invoice() {}

    public Invoice(Date dateStart, Contract contract, Resident resident, Double subtotal, Double vat) {
        this.dateStart = dateStart;
        this.contract = contract;
        this.resident = resident;
        this.subtotal = subtotal;
        this.vat = vat;
        this.total = subtotal * (1 + (vat/100));
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

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
