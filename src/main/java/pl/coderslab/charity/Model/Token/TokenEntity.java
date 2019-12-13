package pl.coderslab.charity.Model.Token;

import pl.coderslab.charity.Model.UserEntity.UserEntity;

import javax.persistence.*;
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

    public TokenEntity() {
    }

    public TokenEntity(UserEntity user) {
        this.user = user;
        this.uuid = UUID.randomUUID().toString();
    }

    public Long getId() {
        return id;
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
