package pl.coderslab.charity.Model.Donation;

import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.Model.Category.CategoryEntity;
import pl.coderslab.charity.Model.Institution.InstitutionEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "donations")
public class DonationEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToMany(mappedBy ="donations", fetch = FetchType.EAGER)
    private List<CategoryEntity> categories;

    @ManyToOne
    @JoinColumn(name="institution_id", nullable=false)
    private InstitutionEntity institution;

    private String street;

    private String city;

    private String zipCode;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;

    private LocalDateTime pickUpTime;

    private String pickUpComment;

    public DonationEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }

    public InstitutionEntity getInstitution() {
        return institution;
    }

    public void setInstitution(InstitutionEntity institution) {
        this.institution = institution;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalDateTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalDateTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public String getPickUpComment() {
        return pickUpComment;
    }

    public void setPickUpComment(String pickUpComment) {
        this.pickUpComment = pickUpComment;
    }
}




//    id
//    quantity (liczba worków)
//    categories (lista obiektów typu Category), pamiętaj o odpowiedniej adnotacji
//    institution (obiekt typu Institution), pamiętaj o odpowiedniej adnotacji.
//        street
//        city
//        zipCode
//        pickUpDate
//        pickUpTime
//        pickUpComment

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDate pickUpDate;
