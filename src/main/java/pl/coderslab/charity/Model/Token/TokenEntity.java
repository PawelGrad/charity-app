package pl.coderslab.charity.Model.Token;

import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.Model.UserEntity.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tokens")
public class TokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity user;

    private String uuid;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime created;



    public Boolean isExprired(){
        return LocalDateTime.now().minusMinutes(5).isAfter(created);
    }

    public TokenEntity() {
    }

    public TokenEntity(UserEntity user) {
        this.user = user;
        this.uuid = UUID.randomUUID().toString();
        this.created = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
