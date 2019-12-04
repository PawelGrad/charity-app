package pl.coderslab.charity.Model.Category;

import pl.coderslab.charity.Model.Donation.DonationEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "category_donation",
            joinColumns = { @JoinColumn(name = "category_id")},
            inverseJoinColumns = {@JoinColumn(name = "donation_id")})
    private List<DonationEntity> donations;


    public CategoryEntity() {
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
}
