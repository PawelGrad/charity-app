package pl.coderslab.charity.Model.Institution;


import pl.coderslab.charity.Model.Donation.DonationEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "institiutions")
public class InstitutionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String descritpion;


    @OneToMany(mappedBy = "institution")
    private List<DonationEntity> donations;


    public InstitutionEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }
}
