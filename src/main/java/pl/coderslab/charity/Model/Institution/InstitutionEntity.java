package pl.coderslab.charity.Model.Institution;


import lombok.Data;
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
    private Boolean archivised;


    @OneToMany(mappedBy = "institution")
    private List<DonationEntity> donations;

    public InstitutionEntity() {
    }

    public Boolean getArchivised() {
        return archivised;
    }

    public void setArchivised(Boolean archivised) {
        this.archivised = archivised;
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

    public List<DonationEntity> getDonations() {
        return donations;
    }

    public void setDonations(List<DonationEntity> donations) {
        this.donations = donations;
    }
}
